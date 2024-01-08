package com.ruoyi.quartz.task;

import com.ruoyi.quartz.service.ISysJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ruoyi.common.utils.StringUtils;

/**
 * 定时任务调度测试
 * 
 * @author ruoyi
 */
@Component("ryTask")
public class RyTask
{

    @Autowired
    private ISysJobService jobService;

    public void ryMultipleParams(String s, Boolean b, Long l, Double d, Integer i)
    {
        System.out.println(StringUtils.format("执行多参方法： 字符串类型{}，布尔类型{}，长整型{}，浮点型{}，整形{}", s, b, l, d, i));
    }

    public void ryParams(String params)
    {
        System.out.println("执行有参方法：" + params);
    }

    public void ryNoParams()
    {
        System.out.println("执行无参方法");
    }

    /**
     * 把所有用户的 免费次数重置
     */
    public void resetting(String companyIds)
    {
        for (int i = 0; i < companyIds.split(",").length; i++) {
            Long companyId = Long.parseLong(companyIds.split(",")[i]);
            Integer freeTime = jobService.selectFreeTime(companyId);
            if(freeTime != null){
                jobService.resetting(freeTime,companyId);
            }
        }
    }

    /**
     * 把所有用户的 免费次数重置
     */
    public void resettinggpg4(Integer times)
    {
        jobService.resettinggpg4(times);
    }

    public void dalleTime(Integer times)
    {
        jobService.dalleTime(times);
    }

    public void mjTime(Integer times)
    {
        jobService.mjTime(times);
    }

    /**
     * 把所有用户的通知已读改成未读
     */
    public void resettingUserNoRead()
    {
        jobService.noRead();
    }
}
