package com.ruoyi.openai.service;

import com.ruoyi.openai.domain.OpenaiUserAccountLog;

import java.util.List;

/**
 * 账户日志Service接口
 * 
 * @author ruoyi
 * @date 2023-08-09
 */
public interface IOpenaiUserAccountLogService 
{
    /**
     * 查询账户日志
     * 
     * @param id 账户日志主键
     * @return 账户日志
     */
    public OpenaiUserAccountLog selectOpenaiUserAccountLogById(Long id);

    /**
     * 查询账户日志列表
     * 
     * @param openaiUserAccountLog 账户日志
     * @return 账户日志集合
     */
    public List<OpenaiUserAccountLog> selectOpenaiUserAccountLogList(OpenaiUserAccountLog openaiUserAccountLog);

    /**
     * 新增账户日志
     * 
     * @param openaiUserAccountLog 账户日志
     * @return 结果
     */
    public int insertOpenaiUserAccountLog(OpenaiUserAccountLog openaiUserAccountLog);

    /**
     * 修改账户日志
     * 
     * @param openaiUserAccountLog 账户日志
     * @return 结果
     */
    public int updateOpenaiUserAccountLog(OpenaiUserAccountLog openaiUserAccountLog);

    /**
     * 批量删除账户日志
     * 
     * @param ids 需要删除的账户日志主键集合
     * @return 结果
     */
    public int deleteOpenaiUserAccountLogByIds(String ids);

    /**
     * 删除账户日志信息
     * 
     * @param id 账户日志主键
     * @return 结果
     */
    public int deleteOpenaiUserAccountLogById(Long id);
}
