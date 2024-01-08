package com.ruoyi.openai.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.openai.domain.OpenaiChatDetail;
import com.ruoyi.openai.domain.OpenaiDalle;
import org.springframework.web.multipart.MultipartFile;


public interface IOpenaiIndexService {

    /**
     * 获取菜单
     * @param userId
     * @param title
     * @return
     */
    AjaxResult getMenu(Long userId,String title);

    /**
     * chatgpt3.5聊天
     * @param prompt
     * @param uuid
     * @param userId
     * @param mId
     * @return
     */
    AjaxResult chat35(String prompt, String uuid, Long userId,Long mId);

    /**
     * chatgpt4 聊天
     * @param prompt
     * @param uuid
     * @param userId
     * @param mId
     * @return
     */
    AjaxResult chat4(String prompt, String uuid, Long userId,Long mId);

    /**
     * 自定义角色模型
     * @param prompt
     * @param uuid
     * @param mId
     * @param userId
     * @return
     */
    AjaxResult chatRole(String prompt, String uuid, Long mId, Long userId);

    /**
     *
     * @param openaiChatDetail
     * @param userId
     * @return
     */
    AjaxResult deleteChatList(OpenaiChatDetail openaiChatDetail,Long userId);

    /**
     * dalle3绘画
     * @param openaiDalle
     * @param userId
     * @return
     */
    AjaxResult dallE3(OpenaiDalle openaiDalle, Long userId);

    /**
     * mj翻译和提示词
     * @param prompts
     * @param type
     * @param userId
     * @return
     */
    AjaxResult mjPrompts(String prompts, Long type, Long userId);

    /**
     * AI识图上传图片
     * @param multipartFile
     * @param uuid
     * @param mId
     * @param userId
     * @return
     */
    AjaxResult uploadPicture(MultipartFile multipartFile, String uuid, Long mId, Long userId);

    /**
     * AI识图 描述内容
     * @param prompt
     * @param uuid
     * @param mId
     * @param userId
     * @return
     */
    AjaxResult readPicture(String prompt, String uuid, Long mId, Long userId);

    AjaxResult deleteMJTaskId(String taskId);
}
