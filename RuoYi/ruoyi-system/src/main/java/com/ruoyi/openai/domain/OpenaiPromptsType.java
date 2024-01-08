package com.ruoyi.openai.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.List;

/**
 * 类型对象 openai_prompts_type
 * 
 * @author ruoyi
 * @date 2023-07-06
 */
@Data
public class OpenaiPromptsType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** type 1:3.5  2:4.0  3:pdf  4:role  */
    private Long type;

    /** companyId */
    private Long companyId;

    /** 模型类型名称 */
    @Excel(name = "模型类型名称")
    private String name;

    /** 排序 */
    @Excel(name = "排序")
    private Long sort;

    private List<OpenaiPrompts> openaiPromptsList;


}
