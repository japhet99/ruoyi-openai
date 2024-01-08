package com.ruoyi.mj.domain;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
@Data
public class ChangeDTO {

    /**
     * state: 自定义参数, task中保留.
     */
    private String state;

    /**
     * UPSCALE(放大); VARIATION(变换); REROLL(重新生成), 示例值(UPSCALE)
     */
    @NotEmpty(message = "action不能为空")
    private String action;

    /**
     * 任务ID: action 为 UPSCALE\VARIATION\RESET 必传. 示例值(1320098173412546)
     */
    @NotEmpty(message = "taskId不能为空")
    private String taskId;
    /**
     *
     * 序号(1~4), action为UPSCALE,VARIATION时必传,示例值(1)
     */
    private Integer index;
    /**
     * 回调地址, 为空时使用全局notifyHook
     */
    private String notifyHook;
}
