package com.ruoyi.openai.mapper;

import com.ruoyi.openai.domain.OpenaiOrderLog;

import java.util.List;

/**
 * 支付成功回调Mapper接口
 * 
 * @author ruoyi
 * @date 2023-07-05
 */
public interface OpenaiOrderLogMapper 
{
    /**
     * 查询支付成功回调
     * 
     * @param id 支付成功回调主键
     * @return 支付成功回调
     */
    public OpenaiOrderLog selectOpenaiOrderLogById(Long id);

    /**
     * 查询支付成功回调列表
     * 
     * @param openaiOrderLog 支付成功回调
     * @return 支付成功回调集合
     */
    public List<OpenaiOrderLog> selectOpenaiOrderLogList(OpenaiOrderLog openaiOrderLog);

    /**
     * 新增支付成功回调
     * 
     * @param openaiOrderLog 支付成功回调
     * @return 结果
     */
    public int insertOpenaiOrderLog(OpenaiOrderLog openaiOrderLog);

    /**
     * 修改支付成功回调
     * 
     * @param openaiOrderLog 支付成功回调
     * @return 结果
     */
    public int updateOpenaiOrderLog(OpenaiOrderLog openaiOrderLog);

    /**
     * 删除支付成功回调
     * 
     * @param id 支付成功回调主键
     * @return 结果
     */
    public int deleteOpenaiOrderLogById(Long id);

    /**
     * 批量删除支付成功回调
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOpenaiOrderLogByIds(String[] ids);
}
