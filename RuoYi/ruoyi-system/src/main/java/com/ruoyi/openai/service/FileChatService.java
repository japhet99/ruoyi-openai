package com.ruoyi.openai.service;

import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.web.multipart.MultipartFile;

public interface FileChatService {

    /**
     * 上传文件，并对文件进行处理后存入向量库，生成总结
     * @param file
     * @param uuid
     * @param mId
     * @param userId
     * @return
     */
    AjaxResult uploadFile(MultipartFile file, String uuid,Long mId,Long userId);

    /**
     *
     * @param prompt
     * @param uuid
     * @param userId
     * @return
     */
    AjaxResult streamChatWithFile(String prompt, String uuid,Long mId, Long userId);

    /**
     *  与文件进行对话
     * @param userId
     * @param sessionId
     * @param message
     * @param apiKey
     * @param chatGPTReq
     * @param usePinecone
     * @return
     */
//    ChatGPTResp chatWithFile(String userId, Integer sessionId, String message, String apiKey, ChatGPTReq chatGPTReq, boolean usePinecone);

    /**
     *  删除向量库的索引，不可恢复
     * @param userId
     * @return
     */
//    boolean dropCollection(String userId);

    /**
     *  与文件进行对话，流式返回
     * @param userId
     * @param message
     */
//    void streamChatWithFile(String userId, String message);

}
