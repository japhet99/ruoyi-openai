package com.ruoyi.openai.service;

import com.ruoyi.openai.domain.OpenaiChatDetail;

import java.util.List;

/**
 * 聊天记录详情Service接口
 * 
 * @author ruoyi
 * @date 2023-05-23
 */
public interface IOpenaiChatDetailService 
{
    /**
     * 查询聊天记录详情
     * 
     * @param id 聊天记录详情主键
     * @return 聊天记录详情
     */
    public OpenaiChatDetail selectOpenaiChatDetailById(Long id);

    /**
     * 查询聊天记录详情列表
     * 
     * @param openaiChatDetail 聊天记录详情
     * @return 聊天记录详情集合
     */
    public List<OpenaiChatDetail> selectOpenaiChatDetailList(OpenaiChatDetail openaiChatDetail);

    /**
     * 新增聊天记录详情
     * 
     * @param openaiChatDetail 聊天记录详情
     * @return 结果
     */
    public int insertOpenaiChatDetail(OpenaiChatDetail openaiChatDetail);

    /**
     * 修改聊天记录详情
     * 
     * @param openaiChatDetail 聊天记录详情
     * @return 结果
     */
    public int updateOpenaiChatDetail(OpenaiChatDetail openaiChatDetail);

    /**
     * 批量删除聊天记录详情
     * 
     * @param ids 需要删除的聊天记录详情主键集合
     * @return 结果
     */
    public int deleteOpenaiChatDetailByIds(String ids);

    /**
     * 删除聊天记录详情信息
     * 
     * @param id 聊天记录详情主键
     * @return 结果
     */
    public int deleteOpenaiChatDetailById(Long id);

    /**
     * 获取最后几条数据
     * @param id
     * @param count
     * @return
     */
    List<OpenaiChatDetail> selectLastList(String id, int count);
}
