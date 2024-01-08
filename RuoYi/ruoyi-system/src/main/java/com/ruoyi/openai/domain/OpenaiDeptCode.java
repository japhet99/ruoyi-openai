package com.ruoyi.openai.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户邀请码对象 openai_dept_code
 * 
 * @author ruoyi
 * @date 2023-06-01
 */
@Data
public class OpenaiDeptCode extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** companyId */
    private Long companyId;

    /** 邀请码 */
    @Excel(name = "邀请码")
    private String code;

    /** 部门id */
    @Excel(name = "部门id")
    private Long userId;

    /** 状态 0有效 1无效 */
    @Excel(name = "状态 0有效 1无效")
    private Integer status;

}
