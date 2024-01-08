package com.ruoyi.openai.mapper;

import com.ruoyi.openai.domain.OpenaiDeptCode;

import java.util.List;

/**
 * 用户邀请码Mapper接口
 * 
 * @author ruoyi
 * @date 2023-06-01
 */
public interface OpenaiDeptCodeMapper 
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
     * 删除用户邀请码
     * 
     * @param id 用户邀请码主键
     * @return 结果
     */
    public int deleteOpenaiDeptCodeById(Long id);

    /**
     * 批量删除用户邀请码
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOpenaiDeptCodeByIds(String[] ids);

    /**
     * 根据userId查询
     * @param userId
     * @return
     */
    OpenaiDeptCode selectOpenaiDeptCodeByUserId(Long userId);
}
