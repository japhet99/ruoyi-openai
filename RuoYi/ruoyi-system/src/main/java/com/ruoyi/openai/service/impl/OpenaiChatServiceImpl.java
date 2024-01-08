package com.ruoyi.openai.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.openai.domain.OpenaiChat;
import com.ruoyi.openai.mapper.OpenaiChatMapper;
import com.ruoyi.openai.service.IOpenaiChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 聊天记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-05-23
 */
@Service
public class OpenaiChatServiceImpl implements IOpenaiChatService
{
    @Autowired
    private OpenaiChatMapper openaiChatMapper;

    /**
     * 查询聊天记录
     * 
     * @param id 聊天记录主键
     * @return 聊天记录
     */
    @Override
    public OpenaiChat selectOpenaiChatById(Long id)
    {
        return openaiChatMapper.selectOpenaiChatById(id);
    }

    /**
     * 查询聊天记录列表
     * 
     * @param openaiChat 聊天记录
     * @return 聊天记录
     */
    @Override
    public List<OpenaiChat> selectOpenaiChatList(OpenaiChat openaiChat)
    {
        return openaiChatMapper.selectOpenaiChatList(openaiChat);
    }

    /**
     * 新增聊天记录
     * 
     * @param openaiChat 聊天记录
     * @return 结果
     */
    @Override
    public int insertOpenaiChat(OpenaiChat openaiChat)
    {
        openaiChat.setCreateTime(DateUtils.getNowDate());
        return openaiChatMapper.insertOpenaiChat(openaiChat);
    }

    /**
     * 修改聊天记录
     * 
     * @param openaiChat 聊天记录
     * @return 结果
     */
    @Override
    public int updateOpenaiChat(OpenaiChat openaiChat)
    {
        openaiChat.setUpdateTime(DateUtils.getNowDate());
        return openaiChatMapper.updateOpenaiChat(openaiChat);
    }

    /**
     * 批量删除聊天记录
     * 
     * @param ids 需要删除的聊天记录主键
     * @return 结果
     */
    @Override
    public int deleteOpenaiChatByIds(String ids)
    {
        return openaiChatMapper.deleteOpenaiChatByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除聊天记录信息
     * 
     * @param id 聊天记录主键
     * @return 结果
     */
    @Override
    public int deleteOpenaiChatById(Long id)
    {
        return openaiChatMapper.deleteOpenaiChatById(id);
    }

    /**
     * 查询我的聊天记录
     * @param userId
     * @param id
     * @return
     */
    @Override
    public List<Map<String,Object>> selectMyChat(Long userId, Long id) {
        return openaiChatMapper.selectMyChat(userId,id);
    }
}
