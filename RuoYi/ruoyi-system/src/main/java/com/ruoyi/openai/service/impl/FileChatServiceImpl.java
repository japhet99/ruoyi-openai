package com.ruoyi.openai.service.impl;

import com.github.pagehelper.PageHelper;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.OpenAIConst;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.domain.DataSqlEntity;
import com.ruoyi.common.req.ChatGPTReq;
import com.ruoyi.common.req.ContextMessage;
import com.ruoyi.common.resp.ChatGPTResp;
import com.ruoyi.common.resp.Embedding;
import com.ruoyi.common.resp.EmbeddingResp;
import com.ruoyi.common.utils.MilvusClientUtil;
import com.ruoyi.common.utils.RecursiveCharacterTextSplitter;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.openai.async.SocketAsync;
import com.ruoyi.openai.domain.*;
import com.ruoyi.openai.emun.ModelEnum;
import com.ruoyi.openai.listener.ConsoleEventSourceListener;
import com.ruoyi.openai.service.*;
import com.ruoyi.system.service.ISysConfigService;
import com.unfbx.chatgpt.entity.chat.ChatCompletion;
import com.unfbx.chatgpt.entity.chat.Message;
import io.milvus.client.MilvusServiceClient;
import io.milvus.grpc.MutationResult;
import io.milvus.grpc.SearchResults;
import io.milvus.param.IndexType;
import io.milvus.param.MetricType;
import io.milvus.param.R;
import io.milvus.param.index.CreateIndexParam;
import io.milvus.response.SearchResultsWrapper;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FileChatServiceImpl implements FileChatService {

    private static final Logger log = LoggerFactory.getLogger(SocketAsync.class);

    @Value("${ruoyi.socketIoUrl}")
    private String socketIoUrl;
    @Autowired
    private SocketAsync socketAsync;
    @Autowired
    private IOpenaiChatService openaiChatService;
    @Autowired
    private IOpenaiChatDetailService openaiChatDetailService;
    @Autowired
    private com.ruoyi.openai.service.SocketIOService socketIOService;
    @Autowired
    private ISysConfigService configService;
    @Autowired
    private IOpenaiUserInfoService openaiUserInfoService;
    @Autowired
    private IOpenaiPromptsService openaiPromptsService;
    @Autowired
    private IOpenaiPromptsTypeService openaiPromptsTypeService;

    // 初始化向量库
    private static final MilvusServiceClient milvusClient = MilvusClientUtil.getMilvusClient();

    private static final String CONTENT_FIELD = "docContent";
    private static final String VECTOR_FIELD = "docEmbed";
    private static final String INDEX_NAME = "docEmbedIndex";
    private static final IndexType INDEX_TYPE = IndexType.IVF_FLAT;
    private static final String INDEX_PARAM = "{\"nlist\":1024}";

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult uploadFile(MultipartFile multipartFile, String uuid,Long mId,Long userId) {
        String word = " <br/> <div class=\"upload-container\"> <svg t=\"1693153944798\" class=\"icon\" viewBox=\"0 0 1024 1024\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" p-id=\"4946\" width=\"24\" height=\"24\"><path d=\"M804.23424 866.5344 630.5792 866.5344c-72.14592 0-129.62816-58.69568-129.62816-129.62816L500.95104 494.7712c0-9.78432 8.55552-18.34496 18.34496-18.34496 9.7792 0 18.34496 8.56064 18.34496 18.34496l0 240.91136c0 51.36896 41.57952 92.94336 92.93824 92.94336l173.65504 0c86.82496 0 158.98112-64.81408 163.87072-147.968 2.44736-44.02176-12.23168-86.82496-42.8032-118.62528-30.57152-31.80032-70.92736-50.14016-116.1728-50.14016l-3.67104 0c-6.11328 0-11.00288-2.44736-14.67392-6.11328-3.66592-4.8896-4.89472-9.7792-3.66592-15.89248 7.33696-35.46624 8.56064-72.15616 1.21856-107.6224-20.79232-118.62016-119.84384-209.11616-239.68768-222.5664-77.04576-8.56064-152.86272 15.8976-211.56352 68.48C279.6032 279.53664 245.36576 352.91136 245.36576 431.17568c0 8.56064-6.11328 17.11616-15.8976 18.34496-95.3856 13.45024-165.0944 97.83808-161.42336 195.66592 3.67104 100.2752 92.94336 183.43424 198.11328 183.43424l84.3776 0c9.78432 0 18.34496 8.55552 18.34496 18.33984 0 9.78944-8.56064 17.1264-19.56864 17.1264l-84.3776 0c-123.51488 0-228.6848-97.82784-233.5744-217.6768-4.89472-111.28832 70.92736-207.8976 177.32096-231.13728 4.8896-81.93536 41.57952-158.98112 103.94624-214.01088 66.03776-58.69568 151.63904-86.82496 240.91648-77.04064 135.74144 13.45024 248.24832 117.4016 271.48288 251.91936 6.11328 34.24256 7.33696 67.26144 2.44736 100.2752 47.6928 3.67104 92.93824 25.68704 124.73344 61.14304 37.90848 40.36096 56.25344 91.7248 52.58752 146.75456C998.67648 785.82272 910.62784 866.5344 804.23424 866.5344L804.23424 866.5344z\" fill=\"#2c2c2c\" p-id=\"4947\"></path><path d=\"M663.59808 631.73632c-4.8896 0-9.78432-1.22368-13.45536-4.8896l-132.0704-133.2992L385.9968 625.61792c-7.33696 7.34208-18.34496 7.34208-25.68192 0-7.33696-7.33696-7.33696-18.34496 0-25.68192l145.52576-145.52576c7.33696-7.33696 19.56352-7.33696 25.68192 0l145.53088 145.52576c7.33696 7.33696 7.33696 18.34496 0 25.68192C673.38752 629.29408 668.48768 631.73632 663.59808 631.73632L663.59808 631.73632z\" fill=\"#2c2c2c\" p-id=\"4948\"></path></svg> <input type=\"file\" onchange=\"uploadFile()\" id=\"myFile\" accept=\".pdf\"><div>上传pdf</div> </div>";
        Long companyId = 1L;
        PDDocument document;
        String doc = "";
        String originalFilename = "";
        String prefixStr;
        String filePath = "";
        File file;
        try {
            OpenaiUserInfo openaiUserInfo1 = openaiUserInfoService.selectOpenaiUserInfoByUserId(userId);
            companyId = openaiUserInfo1.getCompanyId();

            originalFilename = multipartFile.getOriginalFilename();

            //文件大小校验
            String maxFileSize = configService.selectConfigByKeyAndCompanyId(companyId,"max_file_size");
            Integer maxFileSizes = Integer.parseInt(maxFileSize);
            maxFileSizes = maxFileSizes*1024*1024;

            long fileSize = multipartFile.getSize();
            if (fileSize > maxFileSizes) {
                SendEndMsg("文件不能超过"+maxFileSize+"兆"+",可以在线进行分割PDF<a style='color:red;cursor:pointer;' href=\"https://www.ilovepdf.com/zh-cn/split_pdf\" target=\"_blank\">点我过去</a> "+word,userId.toString(),true,originalFilename,uuid,mId,companyId);
                return AjaxResult.success();
            }

            //判断用户的免费次数是否用完
            if (openaiUserInfo1.getMoneyTimeFour() <= 0) {
                log.info("免费次数已用完");
                SendEndMsg("客官,会员已过期!明天再来使用免费次数或 <span style='color:red;cursor:pointer;' onclick='goPage(\"\",\"price\")'> 点我去订阅 </span>&nbsp;&nbsp;<span style='color:red;cursor:pointer;' onclick='goPage(\"\",\"promote\")'> 点我去分享得次数 </span> ",userId.toString(),true,originalFilename,uuid,mId,companyId);
                return AjaxResult.success();
            }


            filePath = FileUploadUtils.upload(RuoYiConfig.getAIPDFPath(), multipartFile, MimeTypeUtils.FILE_EXTENSION);

            String[] filename = originalFilename.split("\\.");

            prefixStr = String.join(".", Arrays.copyOfRange(filename, 0, filename.length - 1));

            file = File.createTempFile(UUID.randomUUID().toString(), "." + filename[filename.length-1]);
            multipartFile.transferTo(file);

            document = PDDocument.load(file);
            PDFTextStripper pdfStripper = new PDFTextStripper();
            doc = pdfStripper.getText(document);

            file.deleteOnExit();
            document.close();

            if(doc.trim().length()<=0){
                SendEndMsg("未找到文件内容，请确保文件包含文字，PDF内不能全是图片，请重新操作! "+word ,userId.toString(),true,originalFilename,uuid,mId,companyId);
                return AjaxResult.success();
            }

        } catch (Exception e) {
            SendEndMsg("文件上传失败，请重新操作! "+word ,userId.toString(),true,originalFilename,uuid,mId,companyId);
            return AjaxResult.success();
        }

        String collectionName = "id_"+userId+ "_" + uuid;

        // 生成向量数据
        DataSqlEntity dataSqlEntity = docToEmbed(companyId,doc);
        if(dataSqlEntity == null){
            SendEndMsg("生成向量数据失败，请重新操作!"+word,userId.toString(),true,originalFilename,uuid,mId,companyId);
            return AjaxResult.success();
        }

        String result = "";
        MilvusClientUtil.createCollection(8000, collectionName);
        R<MutationResult> insertResp = MilvusClientUtil.insert(dataSqlEntity, collectionName);
        MilvusClientUtil.handleResponseStatus(insertResp);
        // 生成索引
        milvusClient.createIndex(CreateIndexParam.newBuilder()
                .withCollectionName(collectionName)
                .withFieldName(VECTOR_FIELD)
                .withIndexName(INDEX_NAME)
                .withIndexType(INDEX_TYPE)
                .withMetricType(MetricType.IP)
                .withExtraParam(INDEX_PARAM)
                .withSyncMode(Boolean.TRUE)
                .build());

        String aa = "Please summarize the extracted parts of a long document in detail and generate three very brief next questions that the user would likely ask next.(about 500-800 tokens)\n" +
                "[Example]:\n" +
                "总结：xxxxxx\n" +
                "问题：\n" +
                "1.xxxxxx\n" +
                "2.xxxxxx\n" +
                "3.xxxxxx";
        String summaryMessage = getContext(companyId,collectionName, aa );
        if(summaryMessage==null){
            SendEndMsg("网络异常，请重新操作!"+word,userId.toString(),true,originalFilename,uuid,mId,companyId);
            return AjaxResult.success();
        }

        OpenaiPrompts openaiPrompts = openaiPromptsService.selectOpenaiPromptsById(mId);
        OpenaiPromptsType openaiPromptsType = openaiPromptsTypeService.selectOpenaiPromptsTypeById(openaiPrompts.getTId());
        ConsoleEventSourceListener eventSourceListener = new ConsoleEventSourceListener(socketIOService, openaiChatDetailService,openaiUserInfoService, userId.toString(), uuid,mId,openaiPromptsType.getType());
        List<Message> messageList = new ArrayList<>();
        Message message = Message.builder().role(Message.Role.USER).content(summaryMessage).build();
        messageList.add(message);
        ChatCompletion chatCompletion = ChatCompletion.builder().model(openaiPrompts.getModel()).messages(messageList).build();
        socketAsync.chatGpt(companyId,eventSourceListener, chatCompletion);
//        System.out.println("========="+resp.toString());

        //聊天记录
        OpenaiChat openaiChat = new OpenaiChat();
        openaiChat.setTitle(originalFilename);
        openaiChat.setUuid(uuid);
        openaiChat.setUserId(userId);
        openaiChat.setMId(mId);
        openaiChatService.insertOpenaiChat(openaiChat);

        //聊天记录表详情
        OpenaiChatDetail openaiChatDetail = new OpenaiChatDetail();
        openaiChatDetail.setContent("<a style='color:blue' target='_blank' href='"+socketIoUrl+filePath+"'>"+originalFilename+"</a>");
        openaiChatDetail.setContent2(summaryMessage);
        openaiChatDetail.setRole(Message.Role.USER.getName());
        openaiChatDetail.setUuid(uuid);
        openaiChatDetail.setUserId(userId);
        openaiChatDetail.setMId(mId);
        openaiChatDetail.setCompanyId(companyId);
        openaiChatDetailService.insertOpenaiChatDetail(openaiChatDetail);

        SendEndMsg(originalFilename,userId.toString(),false,originalFilename,uuid,mId,companyId);


        //释放连接
//        MilvusClientUtil.releaseCollection(collectionName);
        return AjaxResult.success();
    }


    // 文件流式对话
    @Override
    public AjaxResult streamChatWithFile(String prompt ,String uuid ,Long mId, Long userId){

        Long companyId = 1L;

        OpenaiUserInfo openaiUserInfo1 = openaiUserInfoService.selectOpenaiUserInfoByUserId(userId);
        companyId = openaiUserInfo1.getCompanyId();


        //判断用户的免费次数是否用完
        if (openaiUserInfo1.getMoneyTimeFour() <= 0) {
            log.info("免费次数已用完");
            SendEndMsg("客官,ChatGPT4.0 次数已用完! <span style='color:red;cursor:pointer;' onclick='goPage(\"\",\"price\")'> 点我去订阅 </span> &nbsp;&nbsp;<span style='color:red;cursor:pointer;' onclick='goPage(\"\",\"promote\")'> 点我去分享得次数 </span> ",userId.toString(),true,prompt,uuid,mId,companyId);
            return AjaxResult.success();
        }

        String collectionName = "id_"+userId+ "_" + uuid;

        List<OpenaiChatDetail> openaiChatDetails = new ArrayList<>();

        String newQaPrompt = getStandaloneQuestion(prompt,openaiChatDetails);

        ChatGPTReq cgr = ChatGPTReq.builder().temperature(0.0).messages(
                Collections.singletonList(new ContextMessage(Message.Role.USER.getName(), newQaPrompt))).build();

        String newMessage;


        ChatGPTResp qaresp = socketAsync.sessionReq(companyId,cgr);

        if(qaresp == null || qaresp.getMessage().isEmpty()){
            newMessage = prompt;
            log.error("远方的ChatGPT对userId={}的用户在session_id={}的会话中优化问题失败！！");
        }
        else {
            newMessage = qaresp.getMessage();
            log.info("问题优化成功，新的问题是：{}", newMessage);
        }

        // 根据优化后的问题 和 索引信息，构造文件对话问题。如果需要用回v1版本只需要将windowRecords设置为null
        String dialogueMessage = getContext(companyId,collectionName, newMessage);
        log.info("dialogueMessage:{} ", dialogueMessage);
        if(StringUtils.isEmpty(dialogueMessage)){
            SendEndMsg("网络异常，请稍后重试!",userId.toString(),true,prompt,uuid,mId,companyId);
            return AjaxResult.success();
        }

        OpenaiChatDetail openaiChatDetail = new OpenaiChatDetail();
        openaiChatDetail.setContent(prompt);
        openaiChatDetail.setContent2(dialogueMessage);
        openaiChatDetail.setRole(Message.Role.USER.getName());
        openaiChatDetail.setUuid(uuid);
        openaiChatDetail.setUserId(userId);
        openaiChatDetail.setMId(mId);
        openaiChatDetailService.insertOpenaiChatDetail(openaiChatDetail);

        OpenaiChat openaiChat2 = new OpenaiChat();
        openaiChat2.setUuid(uuid);
        openaiChat2.setMId(mId);
        List<OpenaiChat> openaiChats = openaiChatService.selectOpenaiChatList(openaiChat2);

        List<Message> messageList = new ArrayList<>();
        //保存聊天记录
        if (openaiChats.size() == 0) {
            openaiChats = new ArrayList<>();
            //新窗口
            OpenaiChat openaiChat = new OpenaiChat();
            openaiChat.setTitle(prompt);
            openaiChat.setUuid(uuid);
            openaiChat.setUserId(userId);
            openaiChat.setMId(mId);
            openaiChatService.insertOpenaiChat(openaiChat);

            Message message = Message.builder().role(Message.Role.USER).content(dialogueMessage).build();
            messageList.add(message);

        } else {

            OpenaiChatDetail openaiChatDetail2 = new OpenaiChatDetail();
            openaiChatDetail2.setUuid(uuid);
            openaiChatDetail2.setStatus(0);
            PageHelper.orderBy("id desc");
            openaiChatDetails = openaiChatDetailService.selectOpenaiChatDetailList(openaiChatDetail2);
            Collections.reverse(openaiChatDetails);
            for (OpenaiChatDetail chatDetail : openaiChatDetails) {
                Message message1 = new Message();
                message1.setRole(chatDetail.getRole());
                message1.setContent(chatDetail.getContent2());
                messageList.add(message1);
            }
        }

        OpenaiPrompts openaiPrompts = openaiPromptsService.selectOpenaiPromptsById(mId);
        OpenaiPromptsType openaiPromptsType = openaiPromptsTypeService.selectOpenaiPromptsTypeById(openaiPrompts.getTId());
        ConsoleEventSourceListener eventSourceListener = new ConsoleEventSourceListener(socketIOService, openaiChatDetailService,openaiUserInfoService, userId.toString(), uuid,mId,openaiPromptsType.getType());
        ChatCompletion chatCompletion = ChatCompletion.builder().model(openaiPrompts.getModel()).messages(messageList).build();
        socketAsync.chatGpt(companyId,eventSourceListener, chatCompletion);

        return AjaxResult.success();
    }


    /**
     *  文章转成向量
     * @param companyId
     * @param doc
     * @return
     */
    private DataSqlEntity docToEmbed(Long companyId,String doc) {

        DataSqlEntity dse = new DataSqlEntity();
        int tokens = 0;
        RecursiveCharacterTextSplitter textSplitter = new RecursiveCharacterTextSplitter(null, 1000, 200);

        List<String> content = textSplitter.splitText(doc);

        if( content.size() >= 320){
            return null;
        }

        EmbeddingResp embeddingResp = socketAsync.embeddings(companyId,content);
        if(embeddingResp == null){
            return null;
        }
        List<List<Float>> ll = embeddingResp.getData().stream().map(Embedding::getEmbedding).collect(Collectors.toList());
        tokens += embeddingResp.getUsage().getTotal_tokens();

        dse.setLl(ll);
        dse.setContent(content);
        dse.setTotal_token(tokens);
        return dse;
    }


    /**
     *  搜索相似上下文，并且构造最终问题
     * @param collectionName
     * @param message
     * @return
     */
    private String getContext(Long companyId,String collectionName, String message) {
        /* ↓检索文档 */
        EmbeddingResp embed = socketAsync.embeddings(companyId,Collections.singletonList(message));
        if(embed == null){
            return null;
        }
        List<String> orderedCandidates;

        MilvusClientUtil.loadCollection(collectionName);
        List<List<Float>> qaEmbed = embed.getData().stream().map(Embedding::getEmbedding).collect(Collectors.toList());
        R<SearchResults> searchResults = MilvusClientUtil.searchContent(qaEmbed, collectionName);
        SearchResultsWrapper wrapper = new SearchResultsWrapper(searchResults.getData().getResults());
        orderedCandidates = (List<String>) wrapper.getFieldData(CONTENT_FIELD, 0);


        // 添加相关上下文在最终问答
        StringBuilder context = new StringBuilder();
        for(String candidate: orderedCandidates){
            context.append(candidate);
        }

        String bb = "You are an AI assistant providing helpful advice. You are given the following extracted parts of a long document and a part of the chat history, along with a current question. Provide a conversational answer based on the context and the chat histories provided (You can refer to the chat history to know what the user has asked and thus better answer the current question, but you are not allowed to reply to the previous question asked by the user again). If you can't find the answer in the context below, just say \"Hmm, I'm not sure.\" Don't try to make up an answer. If the question is not related to the context, politely respond that you are tuned to only answer questions that are related to the context. \n" +
                "Context information is below:\n" +
                "=========\n" +
                "%s\n" +
                "=========\n" +
                "Chat history is below:\n" +
                "=========\n" +
                "%s\n" +
                "=========\n" +
                "Current Question: %s (Note: Remember, you only need to reply to me in Chinese and try to increase the content of the reply as much as possible to improve the user experience. I believe you can definitely)";
        return String.format(bb, context.toString(), "", message);
    }

    // 获取一个独立的问题，确保ChatGPT不会回答文章无关的内容
    private String getStandaloneQuestion(String message,  List<OpenaiChatDetail>  openaiChatDetails){

        message = message.trim().replace("\n", " ");
        String qaPromptTemplate = "Given the following conversation and a follow up question, rephrase the follow up question to be a standalone English question.\n" +
                "Chat History is below:\n" +
                "%s\n" +
                "Follow Up Input: \n" +
                "%s\n" +
                "Standalone English question: ";

        StringBuilder chatHistory = new StringBuilder();
        for (OpenaiChatDetail openaiChatDetail : openaiChatDetails) {
            chatHistory.append(openaiChatDetail.getRole() + ": "
                    + openaiChatDetail.getContent() + "\n");
        }

        // 优化问题
        return String.format(qaPromptTemplate, chatHistory, message);
    }


    /**
     * 发送消息
     * @param content 发送消息的内容
     * @param userId userId
     * @param temp true:把内容插入数据库 false:不插入
     * @param prompt prompt
     * @param uuid uuid
     * @param mId 模型id
     * @param companyId companyId
     */
    public void SendEndMsg(String content,String userId,boolean temp ,String prompt,String uuid,Long mId,Long companyId){

        PushMessage pushMessage = new PushMessage();
        pushMessage.setContent(content);
        pushMessage.setLoginUserNum(userId);
        socketIOService.pushMessageToUser(pushMessage);

        PushMessage pushMessage2 = new PushMessage();
        pushMessage2.setContent("[DONE]");
        pushMessage2.setLoginUserNum(userId);
        socketIOService.pushMessageToUser(pushMessage2);
        if(temp){

            OpenaiChatDetail openaiChatDetail = new OpenaiChatDetail();
            openaiChatDetail.setContent(prompt);
            openaiChatDetail.setContent2(prompt);
            openaiChatDetail.setRole(Message.Role.USER.getName());
            openaiChatDetail.setUuid(uuid);
            openaiChatDetail.setUserId(Long.parseLong(userId));
            openaiChatDetail.setMId(mId);
            openaiChatDetail.setCompanyId(companyId);
            openaiChatDetailService.insertOpenaiChatDetail(openaiChatDetail);

            openaiChatDetail = new OpenaiChatDetail();
            openaiChatDetail.setContent(content);
            openaiChatDetail.setContent2(content);
            openaiChatDetail.setRole(Message.Role.ASSISTANT.getName());
            openaiChatDetail.setUuid(uuid);
            openaiChatDetail.setUserId(Long.parseLong(userId));
            openaiChatDetail.setMId(mId);
            openaiChatDetail.setCompanyId(companyId);
            openaiChatDetailService.insertOpenaiChatDetail(openaiChatDetail);
        }
    }

}
