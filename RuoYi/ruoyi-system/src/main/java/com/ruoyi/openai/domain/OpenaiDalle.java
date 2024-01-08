package com.ruoyi.openai.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * DALL.E对象 openai_dalle
 * 
 * @author ruoyi
 * @date 2023-11-27
 */
@Data
public class OpenaiDalle extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 公司id */
    @Excel(name = "公司id")
    private Long companyId;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 关键词 */
    @Excel(name = "关键词")
    private String prompt;

    /** 模型  1: DALL.E 2  2:DALL.E 3 */
    @Excel(name = "模型  1: DALL.E 2  2:DALL.E 3")
    private Integer model;

    /** 像素  1:256x256  2:512x512  3:1024x1024  4:1792x1024  5:1024x1792 */
    @Excel(name = "像素  1:256x256  2:512x512  3:1024x1024  4:1792x1024  5:1024x1792")
    private Integer size;

    /** 绘画数量 */
    @Excel(name = "绘画数量")
    private Long n;

    /** 风格 1:vivid(生动的)  2:natural （自然的） */
    @Excel(name = "风格 1:vivid(生动的)  2:natural ", readConverterExp = "自=然的")
    private Integer style;

    /** 返回的url */
    @Excel(name = "返回的url")
    private String url;

    /** 删除 0显示  1删除 */
    @Excel(name = "删除 0显示  1删除")
    private Integer status;


}
