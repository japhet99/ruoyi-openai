package com.ruoyi.openai.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * FAQ 常用问题解答对象 openai_faq
 * 
 * @author ruoyi
 * @date 2023-05-23
 */
@Data
public class OpenaiFaq extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 类型 （字典表的数据） */
    @Excel(name = "类型 ", readConverterExp = "字=典表的数据")
    private Integer type;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 内容 回答 */
    @Excel(name = "内容 回答")
    private String content;

    /** 排序 */
    @Excel(name = "排序")
    private Integer sort;

    /** 0:正常数据 1:已擦除数据 */
    @Excel(name = "0:正常数据 1:已擦除数据")
    private Integer isErased;

}
