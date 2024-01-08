package com.ruoyi.openai.async;

import cn.hutool.http.ContentType;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.ruoyi.common.constant.OpenAIConst;
import com.ruoyi.common.req.ChatGPTReq;
import com.ruoyi.common.req.ContextMessage;
import com.ruoyi.common.req.EmbeddingReq;
import com.ruoyi.common.resp.ChatGPTResp;
import com.ruoyi.common.resp.EmbeddingResp;
import com.ruoyi.common.utils.OkHttpClientUtil;
import com.ruoyi.openai.domain.OpenaiDalleSub;
import com.ruoyi.openai.listener.ConsoleEventSourceListener;
import com.ruoyi.openai.service.IOpenaiApiKeyService;
import com.ruoyi.system.service.ISysConfigService;
import com.unfbx.chatgpt.ChatGPTClient;
import com.unfbx.chatgpt.OpenAiStreamClient;
import com.unfbx.chatgpt.entity.chat.ChatCompletion;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Component
public class SocketAsync {
    private static final Logger log = LoggerFactory.getLogger(SocketAsync.class);

    private OpenAiStreamClient client;

    private OpenAiStreamClient client2;

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private IOpenaiApiKeyService openaiApiKeyService;

    @Async
    public void chatGpt(Long companyId,ConsoleEventSourceListener eventSourceListener, ChatCompletion chatCompletion) {
//        String apiKey = openaiApiKeyService.roundRobinGetByType();

        String chatgpt35Url = configService.selectConfigByKeyAndCompanyId(companyId,"chatgpt35_url");
        String chatgpt35SecretKey = configService.selectConfigByKeyAndCompanyId(companyId,"chatgpt35_secret_key");
        //创建流式输出客户端
//        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890));
        Proxy proxy = Proxy.NO_PROXY;

        client = OpenAiStreamClient.builder()
                .connectTimeout(50)
                .readTimeout(50)
                .writeTimeout(50)
                .apiKey(chatgpt35SecretKey)
                .proxy(proxy)
                .apiHost(chatgpt35Url)
                .build();
        client.streamChatCompletion(chatCompletion, eventSourceListener);
    }

    @Async
    public void chatGpt4(Long companyId,ConsoleEventSourceListener eventSourceListener, ChatCompletion chatCompletion) {
        String chatgpt4Url = configService.selectConfigByKeyAndCompanyId(companyId,"chatgpt4_url");
        String chatgpt4SecretKey = configService.selectConfigByKeyAndCompanyId(companyId,"chatgpt4_secret_key");
        //创建流式输出客户端
//        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890));
        Proxy proxy = Proxy.NO_PROXY;
        client2 = OpenAiStreamClient.builder()
                .connectTimeout(50)
                .readTimeout(50)
                .writeTimeout(50)
                .apiKey(chatgpt4SecretKey)
                .proxy(proxy)
                .apiHost(chatgpt4Url)
                .build();
        client2.streamChatCompletion(chatCompletion, eventSourceListener);
    }


    /**
     *   文本编码
     * @param input
     * @return
     */
    public EmbeddingResp embeddings(Long companyId,List<String> input){
        EmbeddingReq embeddingReq = EmbeddingReq.builder().input(input).build();

        String chatgpt35Url = configService.selectConfigByKeyAndCompanyId(companyId,"chatgpt35_url");
        String chatgpt35SecretKey = configService.selectConfigByKeyAndCompanyId(companyId,"chatgpt35_secret_key");

//        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890));
        Proxy proxy = Proxy.NO_PROXY;

        OkHttpClient client = new OkHttpClient.Builder()
                .callTimeout(60, TimeUnit.SECONDS)
                .proxy(proxy)
                .build();

        Request request = new Request.Builder()
                .url(chatgpt35Url + "v1/embeddings")
                .post(RequestBody.create(MediaType.parse(ContentType.JSON.getValue()), JSONUtil.parseObj(embeddingReq).toString()))
                .header("Authorization", "Bearer " + chatgpt35SecretKey)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();

            if(!response.isSuccessful()){
                log.error("Embedding异常! {}", response.toString());
                return null;
            }
            String body = response.body().string();
            return JSONUtil.toBean(body, EmbeddingResp.class);
        }
        catch (IOException e) {
            log.error("okHttpClient异常! {}", e.getMessage());
        }
        finally {
            if(response != null){
                response.close();
            }
        }
        return null;
    }


    /**
     * 带上下文的对话
     * Ps：之前使用hutool的HttpRequest写请求，但遇到了handshake_failure 错误。目前换成了OKHttp
     * @param gpt
     * @return
     */
    public ChatGPTResp sessionReq(Long companyId,ChatGPTReq gpt) {

        String chatgpt35Url = configService.selectConfigByKeyAndCompanyId(companyId,"chatgpt35_url");
        String chatgpt35SecretKey = configService.selectConfigByKeyAndCompanyId(companyId,"chatgpt35_secret_key");

//        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890));
        Proxy proxy = Proxy.NO_PROXY;


        OkHttpClient client = new OkHttpClient.Builder()
                .callTimeout(60, TimeUnit.SECONDS)
                .proxy(proxy)
                .build();

        Request request = new Request.Builder()
                .url(chatgpt35Url + OpenAIConst.CHATGPT_MAPPING)
                .post(RequestBody.create(MediaType.parse(ContentType.JSON.getValue()), JSONUtil.parseObj(gpt).toString()))
                .header("Authorization", "Bearer " + chatgpt35SecretKey)
                .build();
        Response response = null;
        try {

            response = client.newCall(request).execute();

            if(!response.isSuccessful()){
                log.error("Embedding异常! {}", response.toString());
                return null;
            }
            String body = response.body().string();
            return JSONUtil.toBean(body, ChatGPTResp.class);
        }
        catch (IOException e) {
            log.error("okHttpClient异常! {}", e.getMessage());
        }
        finally {
            if(response != null){
                response.close();
            }
        }
        return null;
    }

    /**
     * dalle3绘画
     * @param companyId
     * @param openaiDalleSub
     * @return
     */
    public String dallE3(Long companyId,OpenaiDalleSub openaiDalleSub) {

        String chatgptDalleUrl = configService.selectConfigByKeyAndCompanyId(companyId,"chatgptDalleUrl");
        String chatgptDalleSecretKey = configService.selectConfigByKeyAndCompanyId(companyId,"chatgptDalleSecretKey");

//        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890));
        Proxy proxy = Proxy.NO_PROXY;

        String body = null;
        OkHttpClient client = new OkHttpClient.Builder()
                .callTimeout(60, TimeUnit.SECONDS)
                .proxy(proxy)
                .build();

        Request request = new Request.Builder()
                .url(chatgptDalleUrl + OpenAIConst.DALLE_MAPPING)
                .post(RequestBody.create(MediaType.parse(ContentType.JSON.getValue()), JSONUtil.parseObj(openaiDalleSub).toString()))
                .header("Authorization", "Bearer " + chatgptDalleSecretKey)
                .build();
        Response response = null;
        try {

            response = client.newCall(request).execute();

            if(!response.isSuccessful()){
                log.error("DALLE绘画异常! {}", response.toString());
                return null;
            }
            body = response.body().string();
            log.info("DALLE绘画返回内容 {}", body);


//         body = "{\n" +
//                "  \"created\": 1701156563,\n" +
//                "  \"data\": [\n" +
//                "    {\n" +
//                "      \"url\": \"https://oaidalleapiprodscus.blob.core.windows.net/private/org-p0EqI1SNFjPxGPxuRKWYEjwQ/user-J6XMpi8el1kl5dKheVM3oKy2/img-1SgJhjoWC8fAztRINzumfU1k.png?st=2023-11-28T06%3A29%3A23Z&se=2023-11-28T08%3A29%3A23Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2023-11-27T21%3A56%3A48Z&ske=2023-11-28T21%3A56%3A48Z&sks=b&skv=2021-08-06&sig=WUTgj1MNl/PKeujIWbRpd0TtRnjpog0dYHU3TggVO64%3D\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"url\": \"https://oaidalleapiprodscus.blob.core.windows.net/private/org-p0EqI1SNFjPxGPxuRKWYEjwQ/user-J6XMpi8el1kl5dKheVM3oKy2/img-RtJRAIKBYxlg4G2rxuBslaoy.png?st=2023-11-28T06%3A29%3A23Z&se=2023-11-28T08%3A29%3A23Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2023-11-27T21%3A56%3A48Z&ske=2023-11-28T21%3A56%3A48Z&sks=b&skv=2021-08-06&sig=Q9/OlKWO1BZ/2uqhCeUXpD5%2B5XrorWFOib839YNZCxs%3D\"\n" +
//                "    }\n" +
//                "  ]\n" +
//                "}";

        }
        catch (IOException e) {
            log.error("okHttpClient异常! {}", e.getMessage());
        }
        finally {
            if(response != null){
                response.close();
            }
        }
        return body;
    }

    //不是流式返回调用chatgpt
    public ChatGPTResp synchronousChatGpt(Long companyId,List<ContextMessage> messages) {
//        String apiKey = openaiApiKeyService.roundRobinGetByType();

        String chatgpt35Url = configService.selectConfigByKeyAndCompanyId(companyId,"chatgpt35_url");
        String chatgpt35SecretKey = configService.selectConfigByKeyAndCompanyId(companyId,"chatgpt35_secret_key");
        //创建流式输出客户端
//        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890));
        Proxy proxy = Proxy.NO_PROXY;
        ChatGPTReq build = ChatGPTReq.builder().model("gpt-3.5-turbo").messages(messages).build();

        OkHttpClient client = new OkHttpClient.Builder()
                .proxy(proxy)
                .build();

        Request request = new Request.Builder()
                .url(chatgpt35Url + OpenAIConst.CHATGPT_MAPPING)
                .post(RequestBody.create(MediaType.parse(ContentType.JSON.getValue()), JSONUtil.parseObj(build).toString()))
                .header("Authorization", "Bearer " + chatgpt35SecretKey)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();

            if(!response.isSuccessful()){
                log.error("synchronousChatGpt异常! {}", response.toString());
                return null;
            }
            String body = response.body().string();
            return JSONUtil.toBean(body, ChatGPTResp.class);
        }
        catch (IOException e) {
            log.error("okHttpClient异常! {}", e.getMessage());
        }
        finally {
            if(response != null){
                response.close();
            }
        }
        return null;
    }


    /**
     *
     * @param companyId
     * @param openaiDalleSub
     * @return
     *
     *
    //         body = "{\n" +
    //                "  \"created\": 1701156563,\n" +
    //                "  \"data\": [\n" +
    //                "    {\n" +
    //                "      \"url\": \"https://oaidalleapiprodscus.blob.core.windows.net/private/org-p0EqI1SNFjPxGPxuRKWYEjwQ/user-J6XMpi8el1kl5dKheVM3oKy2/img-1SgJhjoWC8fAztRINzumfU1k.png?st=2023-11-28T06%3A29%3A23Z&se=2023-11-28T08%3A29%3A23Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2023-11-27T21%3A56%3A48Z&ske=2023-11-28T21%3A56%3A48Z&sks=b&skv=2021-08-06&sig=WUTgj1MNl/PKeujIWbRpd0TtRnjpog0dYHU3TggVO64%3D\"\n" +
    //                "    },\n" +
    //                "    {\n" +
    //                "      \"url\": \"https://oaidalleapiprodscus.blob.core.windows.net/private/org-p0EqI1SNFjPxGPxuRKWYEjwQ/user-J6XMpi8el1kl5dKheVM3oKy2/img-RtJRAIKBYxlg4G2rxuBslaoy.png?st=2023-11-28T06%3A29%3A23Z&se=2023-11-28T08%3A29%3A23Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2023-11-27T21%3A56%3A48Z&ske=2023-11-28T21%3A56%3A48Z&sks=b&skv=2021-08-06&sig=Q9/OlKWO1BZ/2uqhCeUXpD5%2B5XrorWFOib839YNZCxs%3D\"\n" +
    //                "    }\n" +
    //                "  ]\n" +
    //                "}";
     */
    public String dallE3S(Long companyId, OpenaiDalleSub openaiDalleSub) {
        String chatgptDalleUrl = configService.selectConfigByKeyAndCompanyId(companyId,"chatgptDalleUrl");
        String chatgptDalleSecretKey = configService.selectConfigByKeyAndCompanyId(companyId,"chatgptDalleSecretKey");

        String rspStr = null;
        try {
            rspStr = HttpRequest.post(chatgptDalleUrl + OpenAIConst.DALLE_MAPPING).header("Authorization", "Bearer " + chatgptDalleSecretKey).body(JSONUtil.parseObj(openaiDalleSub).toString()).execute().body();
        }
        catch (Exception e) {
            log.error("异常! {}", e.getMessage());
        }
        return rspStr;
    }
}
