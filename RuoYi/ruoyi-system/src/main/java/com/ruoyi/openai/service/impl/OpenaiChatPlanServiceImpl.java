package com.ruoyi.openai.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.openai.domain.OpenaiChatPlan;
import com.ruoyi.openai.mapper.OpenaiChatPlanMapper;
import com.ruoyi.openai.service.IOpenaiChatPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 套餐计划Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-07-04
 */
@Service
public class OpenaiChatPlanServiceImpl implements IOpenaiChatPlanService
{
    @Autowired
    private OpenaiChatPlanMapper openaiChatPlanMapper;

    /**
     * 查询套餐计划
     * 
     * @param id 套餐计划主键
     * @return 套餐计划
     */
    @Override
    public OpenaiChatPlan selectOpenaiChatPlanById(Long id)
    {
        return openaiChatPlanMapper.selectOpenaiChatPlanById(id);
    }

    /**
     * 查询套餐计划列表
     * 
     * @param openaiChatPlan 套餐计划
     * @return 套餐计划
     */
    @Override
    public List<OpenaiChatPlan> selectOpenaiChatPlanList(OpenaiChatPlan openaiChatPlan)
    {
        return openaiChatPlanMapper.selectOpenaiChatPlanList(openaiChatPlan);
    }

    /**
     * 新增套餐计划
     * 
     * @param openaiChatPlan 套餐计划
     * @return 结果
     */
    @Override
    public int insertOpenaiChatPlan(OpenaiChatPlan openaiChatPlan)
    {
        openaiChatPlan.setCreateTime(DateUtils.getNowDate());
        return openaiChatPlanMapper.insertOpenaiChatPlan(openaiChatPlan);
    }

    /**
     * 修改套餐计划
     * 
     * @param openaiChatPlan 套餐计划
     * @return 结果
     */
    @Override
    public int updateOpenaiChatPlan(OpenaiChatPlan openaiChatPlan)
    {
        openaiChatPlan.setUpdateTime(DateUtils.getNowDate());
        return openaiChatPlanMapper.updateOpenaiChatPlan(openaiChatPlan);
    }

    /**
     * 批量删除套餐计划
     * 
     * @param ids 需要删除的套餐计划主键
     * @return 结果
     */
    @Override
    public int deleteOpenaiChatPlanByIds(String ids)
    {
        return openaiChatPlanMapper.deleteOpenaiChatPlanByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除套餐计划信息
     * 
     * @param id 套餐计划主键
     * @return 结果
     */
    @Override
    public int deleteOpenaiChatPlanById(Long id)
    {
        return openaiChatPlanMapper.deleteOpenaiChatPlanById(id);
    }
}
