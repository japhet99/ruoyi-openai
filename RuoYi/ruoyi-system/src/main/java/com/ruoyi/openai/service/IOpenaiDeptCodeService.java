package com.ruoyi.openai.service;

import com.ruoyi.openai.domain.OpenaiDeptCode;

import java.util.List;

/**
 * 用户邀请码Service接口
 * 
 * @author ruoyi
 * @date 2023-06-01
 */
public interface IOpenaiDeptCodeService 
{
    /**
     * 查询用户邀请码
     * 
     * @param id 用户邀请码主键
     * @return 用户邀请码
     */
    public OpenaiDeptCode selectOpenaiDeptCodeById(Long id);

    /**
     * 查询用户邀请码列表
     * 
     * @param openaiDeptCode 用户邀请码
     * @return 用户邀请码集合
     */
    public List<OpenaiDeptCode> selectOpenaiDeptCodeList(OpenaiDeptCode openaiDeptCode);

    /**
     * 新增用户邀请码
     * 
     * @param openaiDeptCode 用户邀请码
     * @return 结果
     */
    public int insertOpenaiDeptCode(OpenaiDeptCode openaiDeptCode);

    /**
     * 修改用户邀请码
     * 
     * @param openaiDeptCode 用户邀请码
     * @return 结果
     */
    public int updateOpenaiDeptCode(OpenaiDeptCode openaiDeptCode);

    /**
     * 批量删除用户邀请码
     * 
     * @param ids 需要删除的用户邀请码主键集合
     * @return 结果
     */
    public int deleteOpenaiDeptCodeByIds(String ids);

    /**
     * 删除用户邀请码信息
     * 
     * @param id 用户邀请码主键
     * @return 结果
     */
    public int deleteOpenaiDeptCodeById(Long id);

    OpenaiDeptCode selectOpenaiDeptCodeByUserId(Long userId);
}
