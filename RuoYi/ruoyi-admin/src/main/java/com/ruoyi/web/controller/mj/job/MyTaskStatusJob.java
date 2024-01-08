package com.ruoyi.web.controller.mj.job;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.mj.domain.MjTaskInfo;
import com.ruoyi.mj.happer.MJHapper;
import com.ruoyi.mj.service.IMjTaskInfoService;
import com.ruoyi.openai.domain.OpenaiUserInfo;
import com.ruoyi.openai.mapper.OpenaiUserInfoMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Component
@EnableScheduling
public class MyTaskStatusJob {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 20秒执行一次
     */
    @Scheduled(cron = "*/40 * * * * *")
    public void checkTaskStatus() {
        log.info("AI绘画定时任务执行了-----------------------------------");
    }

}
