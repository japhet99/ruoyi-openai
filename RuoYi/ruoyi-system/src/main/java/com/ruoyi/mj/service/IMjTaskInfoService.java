package com.ruoyi.mj.service;

import com.ruoyi.mj.domain.MjTaskInfo;

import java.util.List;

/**
 * midjourney 绘画任务Service接口
 * 
 * @author ruoyi
 * @date 2023-09-21
 */
public interface IMjTaskInfoService 
{
    /**
     * 查询midjourney 绘画任务
     * 
     * @param id midjourney 绘画任务主键
     * @return midjourney 绘画任务
     */
    public MjTaskInfo selectMjTaskInfoById(Long id);

    /**
     * 查询midjourney 绘画任务列表
     * 
     * @param mjTaskInfo midjourney 绘画任务
     * @return midjourney 绘画任务集合
     */
    public List<MjTaskInfo> selectMjTaskInfoList(MjTaskInfo mjTaskInfo);

    /**
     * 新增midjourney 绘画任务
     * 
     * @param mjTaskInfo midjourney 绘画任务
     * @return 结果
     */
    public int insertMjTaskInfo(MjTaskInfo mjTaskInfo);

    /**
     * 修改midjourney 绘画任务
     * 
     * @param mjTaskInfo midjourney 绘画任务
     * @return 结果
     */
    public int updateMjTaskInfo(MjTaskInfo mjTaskInfo);

    /**
     * 批量删除midjourney 绘画任务
     * 
     * @param ids 需要删除的midjourney 绘画任务主键集合
     * @return 结果
     */
    public int deleteMjTaskInfoByIds(String ids);

    /**
     * 删除midjourney 绘画任务信息
     * 
     * @param id midjourney 绘画任务主键
     * @return 结果
     */
    public int deleteMjTaskInfoById(Long id);

    /**
     * 根据taskId查询
     * @param taskId
     * @return
     */
    MjTaskInfo selectMjTaskInfoByTaskId(String taskId);
}
