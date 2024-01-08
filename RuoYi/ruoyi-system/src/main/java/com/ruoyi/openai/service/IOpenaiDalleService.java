package com.ruoyi.openai.service;

import com.ruoyi.openai.domain.OpenaiDalle;

import java.util.List;

/**
 * DALL.EService接口
 * 
 * @author ruoyi
 * @date 2023-11-27
 */
public interface IOpenaiDalleService 
{
    /**
     * 查询DALL.E
     * 
     * @param id DALL.E主键
     * @return DALL.E
     */
    public OpenaiDalle selectOpenaiDalleById(Long id);

    /**
     * 查询DALL.E列表
     * 
     * @param openaiDalle DALL.E
     * @return DALL.E集合
     */
    public List<OpenaiDalle> selectOpenaiDalleList(OpenaiDalle openaiDalle);

    /**
     * 新增DALL.E
     * 
     * @param openaiDalle DALL.E
     * @return 结果
     */
    public int insertOpenaiDalle(OpenaiDalle openaiDalle);

    /**
     * 修改DALL.E
     * 
     * @param openaiDalle DALL.E
     * @return 结果
     */
    public int updateOpenaiDalle(OpenaiDalle openaiDalle);

    /**
     * 批量删除DALL.E
     * 
     * @param ids 需要删除的DALL.E主键集合
     * @return 结果
     */
    public int deleteOpenaiDalleByIds(String ids);

    /**
     * 删除DALL.E信息
     * 
     * @param id DALL.E主键
     * @return 结果
     */
    public int deleteOpenaiDalleById(Long id);
}
