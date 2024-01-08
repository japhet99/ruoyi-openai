package com.ruoyi.openai.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * api_key 列对象 openai_api_key
 * 
 * @author ruoyi
 * @date 2023-07-10
 */
@Data
public class OpenaiApiKey extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** api_key */
    @Excel(name = "api_key")
    private String apiKey;

    /** 0：启用  1：禁用 */
    @Excel(name = "0：启用  1：禁用")
    private Integer status;


}
