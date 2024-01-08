package com.ruoyi.openai.service;

import com.ruoyi.openai.domain.OpenaiChat;

import java.util.List;
import java.util.Map;

/**
 * 聊天记录Service接口
 * 
 * @author ruoyi
 * @date 2023-05-23
 */
public interface IOpenaiChatService 
{
    /**
     * 查询聊天记录
     * 
     * @param id 聊天记录主键
     * @return 聊天记录
     */
    public OpenaiChat selectOpenaiChatById(Long id);

    /**
     * 查询聊天记录列表
     * 
     * @param openaiChat 聊天记录
     * @return 聊天记录集合
     */
    public List<OpenaiChat> selectOpenaiChatList(OpenaiChat openaiChat);

    /**
     * 新增聊天记录
     * 
     * @param openaiChat 聊天记录
     * @return 结果
     */
    public int insertOpenaiChat(OpenaiChat openaiChat);

    /**
     * 修改聊天记录
     * 
     * @param openaiChat 聊天记录
     * @return 结果
     */
    public int updateOpenaiChat(OpenaiChat openaiChat);

    /**
     * 批量删除聊天记录
     * 
     * @param ids 需要删除的聊天记录主键集合
     * @return 结果
     */
    public int deleteOpenaiChatByIds(String ids);

    /**
     * 删除聊天记录信息
     * 
     * @param id 聊天记录主键
     * @return 结果
     */
    public int deleteOpenaiChatById(Long id);

    /**
     * 查询我的聊天记录
     * @param userId
     * @param id
     * @return
     */
    List<Map<String,Object>> selectMyChat(Long userId, Long id);
}
