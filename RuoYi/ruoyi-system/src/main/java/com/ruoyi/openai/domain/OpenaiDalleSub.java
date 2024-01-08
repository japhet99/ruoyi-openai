package com.ruoyi.openai.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * DALL.E对象 openai_dalle
 *
 * @date 2023-11-27
 */
@Data
public class OpenaiDalleSub
{

    /** 关键词 */
    @Excel(name = "关键词")
    private String prompt;

    /** 模型  1: DALL.E 2  2:DALL.E 3 */
    private String model;

    /** 像素  1:256x256  2:512x512  3:1024x1024  4:1792x1024  5:1024x1792 */
    private String size;

    /** 绘画数量 */
    private Integer n;

    /** 风格 1:vivid(生动的)  2:natural （自然的） */
    private String style;

}
