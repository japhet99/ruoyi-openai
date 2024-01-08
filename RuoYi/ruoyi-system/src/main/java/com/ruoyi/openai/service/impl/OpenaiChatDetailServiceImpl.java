package com.ruoyi.openai.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.openai.domain.OpenaiChatDetail;
import com.ruoyi.openai.mapper.OpenaiChatDetailMapper;
import com.ruoyi.openai.service.IOpenaiChatDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.core.text.Convert;

/**
 * 聊天记录详情Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-05-23
 */
@Service
public class OpenaiChatDetailServiceImpl implements IOpenaiChatDetailService
{
    @Autowired
    private OpenaiChatDetailMapper openaiChatDetailMapper;

    /**
     * 查询聊天记录详情
     * 
     * @param id 聊天记录详情主键
     * @return 聊天记录详情
     */
    @Override
    public OpenaiChatDetail selectOpenaiChatDetailById(Long id)
    {
        return openaiChatDetailMapper.selectOpenaiChatDetailById(id);
    }

    /**
     * 查询聊天记录详情列表
     * 
     * @param openaiChatDetail 聊天记录详情
     * @return 聊天记录详情
     */
    @Override
    public List<OpenaiChatDetail> selectOpenaiChatDetailList(OpenaiChatDetail openaiChatDetail)
    {
        return openaiChatDetailMapper.selectOpenaiChatDetailList(openaiChatDetail);
    }

    /**
     * 新增聊天记录详情
     * 
     * @param openaiChatDetail 聊天记录详情
     * @return 结果
     */
    @Override
    public int insertOpenaiChatDetail(OpenaiChatDetail openaiChatDetail)
    {
        openaiChatDetail.setCreateTime(DateUtils.getNowDate());
        return openaiChatDetailMapper.insertOpenaiChatDetail(openaiChatDetail);
    }

    /**
     * 修改聊天记录详情
     * 
     * @param openaiChatDetail 聊天记录详情
     * @return 结果
     */
    @Override
    public int updateOpenaiChatDetail(OpenaiChatDetail openaiChatDetail)
    {
        openaiChatDetail.setUpdateTime(DateUtils.getNowDate());
        return openaiChatDetailMapper.updateOpenaiChatDetail(openaiChatDetail);
    }

    /**
     * 批量删除聊天记录详情
     * 
     * @param ids 需要删除的聊天记录详情主键
     * @return 结果
     */
    @Override
    public int deleteOpenaiChatDetailByIds(String ids)
    {
        return openaiChatDetailMapper.deleteOpenaiChatDetailByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除聊天记录详情信息
     * 
     * @param id 聊天记录详情主键
     * @return 结果
     */
    @Override
    public int deleteOpenaiChatDetailById(Long id)
    {
        return openaiChatDetailMapper.deleteOpenaiChatDetailById(id);
    }

    @Override
    public List<OpenaiChatDetail> selectLastList(String id, int count) {
        return openaiChatDetailMapper.selectLastList(id,count);
    }
}
