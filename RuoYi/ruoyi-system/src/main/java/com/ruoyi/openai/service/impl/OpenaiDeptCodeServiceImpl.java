package com.ruoyi.openai.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.openai.domain.OpenaiDeptCode;
import com.ruoyi.openai.mapper.OpenaiDeptCodeMapper;
import com.ruoyi.openai.service.IOpenaiDeptCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.core.text.Convert;

/**
 * 用户邀请码Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-06-01
 */
@Service
public class OpenaiDeptCodeServiceImpl implements IOpenaiDeptCodeService
{
    @Autowired
    private OpenaiDeptCodeMapper openaiDeptCodeMapper;

    /**
     * 查询用户邀请码
     * 
     * @param id 用户邀请码主键
     * @return 用户邀请码
     */
    @Override
    public OpenaiDeptCode selectOpenaiDeptCodeById(Long id)
    {
        return openaiDeptCodeMapper.selectOpenaiDeptCodeById(id);
    }

    /**
     * 查询用户邀请码列表
     * 
     * @param openaiDeptCode 用户邀请码
     * @return 用户邀请码
     */
    @Override
    public List<OpenaiDeptCode> selectOpenaiDeptCodeList(OpenaiDeptCode openaiDeptCode)
    {
        return openaiDeptCodeMapper.selectOpenaiDeptCodeList(openaiDeptCode);
    }

    /**
     * 新增用户邀请码
     * 
     * @param openaiDeptCode 用户邀请码
     * @return 结果
     */
    @Override
    public int insertOpenaiDeptCode(OpenaiDeptCode openaiDeptCode)
    {
        openaiDeptCode.setCreateTime(DateUtils.getNowDate());
        return openaiDeptCodeMapper.insertOpenaiDeptCode(openaiDeptCode);
    }

    /**
     * 修改用户邀请码
     * 
     * @param openaiDeptCode 用户邀请码
     * @return 结果
     */
    @Override
    public int updateOpenaiDeptCode(OpenaiDeptCode openaiDeptCode)
    {
        openaiDeptCode.setUpdateTime(DateUtils.getNowDate());
        return openaiDeptCodeMapper.updateOpenaiDeptCode(openaiDeptCode);
    }

    /**
     * 批量删除用户邀请码
     * 
     * @param ids 需要删除的用户邀请码主键
     * @return 结果
     */
    @Override
    public int deleteOpenaiDeptCodeByIds(String ids)
    {
        return openaiDeptCodeMapper.deleteOpenaiDeptCodeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除用户邀请码信息
     * 
     * @param id 用户邀请码主键
     * @return 结果
     */
    @Override
    public int deleteOpenaiDeptCodeById(Long id)
    {
        return openaiDeptCodeMapper.deleteOpenaiDeptCodeById(id);
    }

    /**
     * 根据userId查询
     * @param userId
     * @return
     */
    @Override
    public OpenaiDeptCode selectOpenaiDeptCodeByUserId(Long userId) {
        return openaiDeptCodeMapper.selectOpenaiDeptCodeByUserId(userId);
    }
}
