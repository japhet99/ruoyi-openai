package com.ruoyi.mj.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.mj.domain.MjTaskInfo;
import com.ruoyi.mj.mapper.MjTaskInfoMapper;
import com.ruoyi.mj.service.IMjTaskInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.core.text.Convert;

/**
 * midjourney 绘画任务Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-09-21
 */
@Service
public class MjTaskInfoServiceImpl implements IMjTaskInfoService
{
    @Autowired
    private MjTaskInfoMapper mjTaskInfoMapper;

    /**
     * 查询midjourney 绘画任务
     * 
     * @param id midjourney 绘画任务主键
     * @return midjourney 绘画任务
     */
    @Override
    public MjTaskInfo selectMjTaskInfoById(Long id)
    {
        return mjTaskInfoMapper.selectMjTaskInfoById(id);
    }

    /**
     * 查询midjourney 绘画任务列表
     * 
     * @param mjTaskInfo midjourney 绘画任务
     * @return midjourney 绘画任务
     */
    @Override
    public List<MjTaskInfo> selectMjTaskInfoList(MjTaskInfo mjTaskInfo)
    {
        return mjTaskInfoMapper.selectMjTaskInfoList(mjTaskInfo);
    }

    /**
     * 新增midjourney 绘画任务
     * 
     * @param mjTaskInfo midjourney 绘画任务
     * @return 结果
     */
    @Override
    public int insertMjTaskInfo(MjTaskInfo mjTaskInfo)
    {
        mjTaskInfo.setCreateTime(DateUtils.getNowDate());
        return mjTaskInfoMapper.insertMjTaskInfo(mjTaskInfo);
    }

    /**
     * 修改midjourney 绘画任务
     * 
     * @param mjTaskInfo midjourney 绘画任务
     * @return 结果
     */
    @Override
    public int updateMjTaskInfo(MjTaskInfo mjTaskInfo)
    {
        mjTaskInfo.setUpdateTime(DateUtils.getNowDate());
        return mjTaskInfoMapper.updateMjTaskInfo(mjTaskInfo);
    }

    /**
     * 批量删除midjourney 绘画任务
     * 
     * @param ids 需要删除的midjourney 绘画任务主键
     * @return 结果
     */
    @Override
    public int deleteMjTaskInfoByIds(String ids)
    {
        return mjTaskInfoMapper.deleteMjTaskInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除midjourney 绘画任务信息
     * 
     * @param id midjourney 绘画任务主键
     * @return 结果
     */
    @Override
    public int deleteMjTaskInfoById(Long id)
    {
        return mjTaskInfoMapper.deleteMjTaskInfoById(id);
    }

    /**
     * 根据taskId查询
     * @param taskId
     * @return
     */
    @Override
    public MjTaskInfo selectMjTaskInfoByTaskId(String taskId) {
        return mjTaskInfoMapper.selectMjTaskInfoByTaskId(taskId);
    }
}
