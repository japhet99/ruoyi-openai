package com.ruoyi.openai.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 聊天记录对象 openai_chat
 * 
 * @author ruoyi
 * @date 2023-05-23
 */
@Data
public class OpenaiChat extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** companyId */
    private Long companyId;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 模型id */
    @Excel(name = "模型id")
    private Long mId;

    /** uuid */
    @Excel(name = "uuid")
    private String uuid;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 删除 0显示  1删除 */
    @Excel(name = "删除 0显示  1删除")
    private Integer status;

}
