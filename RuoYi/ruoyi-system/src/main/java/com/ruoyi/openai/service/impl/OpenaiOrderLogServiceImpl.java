package com.ruoyi.openai.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.openai.domain.OpenaiOrderLog;
import com.ruoyi.openai.mapper.OpenaiOrderLogMapper;
import com.ruoyi.openai.service.IOpenaiOrderLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 支付成功回调Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-07-05
 */
@Service
public class OpenaiOrderLogServiceImpl implements IOpenaiOrderLogService
{
    @Autowired
    private OpenaiOrderLogMapper openaiOrderLogMapper;

    /**
     * 查询支付成功回调
     * 
     * @param id 支付成功回调主键
     * @return 支付成功回调
     */
    @Override
    public OpenaiOrderLog selectOpenaiOrderLogById(Long id)
    {
        return openaiOrderLogMapper.selectOpenaiOrderLogById(id);
    }

    /**
     * 查询支付成功回调列表
     * 
     * @param openaiOrderLog 支付成功回调
     * @return 支付成功回调
     */
    @Override
    public List<OpenaiOrderLog> selectOpenaiOrderLogList(OpenaiOrderLog openaiOrderLog)
    {
        return openaiOrderLogMapper.selectOpenaiOrderLogList(openaiOrderLog);
    }

    /**
     * 新增支付成功回调
     * 
     * @param openaiOrderLog 支付成功回调
     * @return 结果
     */
    @Override
    public int insertOpenaiOrderLog(OpenaiOrderLog openaiOrderLog)
    {
        openaiOrderLog.setCreateTime(DateUtils.getNowDate());
        return openaiOrderLogMapper.insertOpenaiOrderLog(openaiOrderLog);
    }

    /**
     * 修改支付成功回调
     * 
     * @param openaiOrderLog 支付成功回调
     * @return 结果
     */
    @Override
    public int updateOpenaiOrderLog(OpenaiOrderLog openaiOrderLog)
    {
        openaiOrderLog.setUpdateTime(DateUtils.getNowDate());
        return openaiOrderLogMapper.updateOpenaiOrderLog(openaiOrderLog);
    }

    /**
     * 批量删除支付成功回调
     * 
     * @param ids 需要删除的支付成功回调主键
     * @return 结果
     */
    @Override
    public int deleteOpenaiOrderLogByIds(String ids)
    {
        return openaiOrderLogMapper.deleteOpenaiOrderLogByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除支付成功回调信息
     * 
     * @param id 支付成功回调主键
     * @return 结果
     */
    @Override
    public int deleteOpenaiOrderLogById(Long id)
    {
        return openaiOrderLogMapper.deleteOpenaiOrderLogById(id);
    }
}
