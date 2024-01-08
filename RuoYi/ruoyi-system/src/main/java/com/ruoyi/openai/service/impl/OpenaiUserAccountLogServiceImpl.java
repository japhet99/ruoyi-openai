package com.ruoyi.openai.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.openai.domain.OpenaiUserAccountLog;
import com.ruoyi.openai.mapper.OpenaiUserAccountLogMapper;
import com.ruoyi.openai.service.IOpenaiUserAccountLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.core.text.Convert;

/**
 * 账户日志Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-08-09
 */
@Service
public class OpenaiUserAccountLogServiceImpl implements IOpenaiUserAccountLogService
{
    @Autowired
    private OpenaiUserAccountLogMapper openaiUserAccountLogMapper;

    /**
     * 查询账户日志
     * 
     * @param id 账户日志主键
     * @return 账户日志
     */
    @Override
    public OpenaiUserAccountLog selectOpenaiUserAccountLogById(Long id)
    {
        return openaiUserAccountLogMapper.selectOpenaiUserAccountLogById(id);
    }

    /**
     * 查询账户日志列表
     * 
     * @param openaiUserAccountLog 账户日志
     * @return 账户日志
     */
    @Override
    public List<OpenaiUserAccountLog> selectOpenaiUserAccountLogList(OpenaiUserAccountLog openaiUserAccountLog)
    {
        return openaiUserAccountLogMapper.selectOpenaiUserAccountLogList(openaiUserAccountLog);
    }

    /**
     * 新增账户日志
     * 
     * @param openaiUserAccountLog 账户日志
     * @return 结果
     */
    @Override
    public int insertOpenaiUserAccountLog(OpenaiUserAccountLog openaiUserAccountLog)
    {
        openaiUserAccountLog.setCreateTime(DateUtils.getNowDate());
        return openaiUserAccountLogMapper.insertOpenaiUserAccountLog(openaiUserAccountLog);
    }

    /**
     * 修改账户日志
     * 
     * @param openaiUserAccountLog 账户日志
     * @return 结果
     */
    @Override
    public int updateOpenaiUserAccountLog(OpenaiUserAccountLog openaiUserAccountLog)
    {
        openaiUserAccountLog.setUpdateTime(DateUtils.getNowDate());
        return openaiUserAccountLogMapper.updateOpenaiUserAccountLog(openaiUserAccountLog);
    }

    /**
     * 批量删除账户日志
     * 
     * @param ids 需要删除的账户日志主键
     * @return 结果
     */
    @Override
    public int deleteOpenaiUserAccountLogByIds(String ids)
    {
        return openaiUserAccountLogMapper.deleteOpenaiUserAccountLogByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除账户日志信息
     * 
     * @param id 账户日志主键
     * @return 结果
     */
    @Override
    public int deleteOpenaiUserAccountLogById(Long id)
    {
        return openaiUserAccountLogMapper.deleteOpenaiUserAccountLogById(id);
    }
}
