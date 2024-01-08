package com.ruoyi.openai.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 套餐计划对象 openai_chat_plan
 * 
 * @author ruoyi
 * @date 2023-07-04
 */
@Data
public class OpenaiChatPlan extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** companyId */
    private Long companyId;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 0: 3.5模型  1:4.0   2:mj  3:xxx */
    @Excel(name = "0: 3.5模型  1:4.0   2:mj  3:xxx ")
    private Integer model;

    /** 图片 */
    @Excel(name = "图片")
    private String logo;

    /** 0: 按天 1:次 */
    @Excel(name = "0: 按天 1:次")
    private Integer type;

    /** 价格 */
    @Excel(name = "价格")
    private BigDecimal price;

    /** 数值 */
    @Excel(name = "数值")
    private Long time;

    /** 0:是体验  1:不是体验 */
    @Excel(name = "0:是体验  1:不是体验")
    private Integer experienced;
    private boolean experiencedStr;

    /** 删除 0显示  1删除 */
    @Excel(name = "删除 0显示  1删除")
    private Integer status;

    /** 排序 */
    @Excel(name = "排序")
    private Integer sort;
}
