package com.ruoyi.openai.mapper;

import com.ruoyi.openai.domain.OpenaiChatDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 聊天记录详情Mapper接口
 * 
 * @author ruoyi
 * @date 2023-05-23
 */
public interface OpenaiChatDetailMapper 
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
     * 删除聊天记录详情
     * 
     * @param id 聊天记录详情主键
     * @return 结果
     */
    public int deleteOpenaiChatDetailById(Long id);

    /**
     * 批量删除聊天记录详情
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOpenaiChatDetailByIds(String[] ids);

    List<OpenaiChatDetail> selectLastList(@Param("id") String id, @Param("count") int count);
}