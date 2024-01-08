package com.ruoyi.openai.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * prompts对象 openai_prompts
 * 
 * @author ruoyi
 * @date 2023-06-30
 */
@Data
public class OpenaiPrompts extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** companyId */
    private Long companyId;

    /** id */
    private String uuid;

    /** 类型 */
    @Excel(name = "类型")
    private Long tId;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 内容 */
    @Excel(name = "内容")
    private String prompts;

    /** 第一句内容 */
    @Excel(name = "第一句内容")
    private String firstContent;

    /** 发送的提示内容 */
    @Excel(name = "发送的提示内容")
    private String inputPlaceholder;

    /** 模型图片 */
    @Excel(name = "模型图片")
    private String icon;

    /** ChatGPT 模型 */
    @Excel(name = "ChatGPT 模型")
    private String model;

    /** max_tokens */
    @Excel(name = "max_tokens")
    private Long maxTokens;

    /** temperature */
    @Excel(name = "temperature")
    private BigDecimal temperature;

    /** top_p */
    @Excel(name = "top_p")
    private BigDecimal topP;

    /** presence_penalty */
    @Excel(name = "presence_penalty")
    private BigDecimal presencePenalty;

    /** frequency_penalty */
    @Excel(name = "frequency_penalty")
    private BigDecimal frequencyPenalty;

    /** stream  0:true  1:false */
    @Excel(name = "stream  0:true  1:false")
    private Integer stream;

    /** 删除 0显示  1删除 */
    @Excel(name = "删除 0显示  1删除")
    private Integer status;

    /** 排序 */
    @Excel(name = "排序")
    private Integer sort;

    private String content;

}
