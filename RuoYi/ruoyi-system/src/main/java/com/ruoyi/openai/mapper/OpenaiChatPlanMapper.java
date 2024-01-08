package com.ruoyi.openai.mapper;

import com.ruoyi.openai.domain.OpenaiChatPlan;

import java.util.List;

/**
 * 套餐计划Mapper接口
 * 
 * @author ruoyi
 * @date 2023-07-04
 */
public interface OpenaiChatPlanMapper 
{
    /**
     * 查询套餐计划
     * 
     * @param id 套餐计划主键
     * @return 套餐计划
     */
    public OpenaiChatPlan selectOpenaiChatPlanById(Long id);

    /**
     * 查询套餐计划列表
     * 
     * @param openaiChatPlan 套餐计划
     * @return 套餐计划集合
     */
    public List<OpenaiChatPlan> selectOpenaiChatPlanList(OpenaiChatPlan openaiChatPlan);

    /**
     * 新增套餐计划
     * 
     * @param openaiChatPlan 套餐计划
     * @return 结果
     */
    public int insertOpenaiChatPlan(OpenaiChatPlan openaiChatPlan);

    /**
     * 修改套餐计划
     * 
     * @param openaiChatPlan 套餐计划
     * @return 结果
     */
    public int updateOpenaiChatPlan(OpenaiChatPlan openaiChatPlan);

    /**
     * 删除套餐计划
     * 
     * @param id 套餐计划主键
     * @return 结果
     */
    public int deleteOpenaiChatPlanById(Long id);

    /**
     * 批量删除套餐计划
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOpenaiChatPlanByIds(String[] ids);
}
