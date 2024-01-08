package com.ruoyi.openai.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 聊天记录详情对象 openai_chat_detail
 * 
 * @author ruoyi
 * @date 2023-05-23
 */
@Data
public class OpenaiChatDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** type 1:3.5  2:4.0  3:pdf  4:role */
    private Long type;

    /** companyId */
    private Long companyId;

    /** 模型id */
    @Excel(name = "模型id")
    private Long mId;
    private String mName;

    /** 聊天记录表uuid */
    @Excel(name = "聊天记录表uuid")
    private String uuid;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 问题 / 回答 */
    @Excel(name = "问题 / 回答")
    private String content;

    /** pdf整理后的 问题 / 回答 */
    @Excel(name = "pdf整理后的 问题 / 回答")
    private String content2;

    /** 角色*/
    @Excel(name = "角色")
    private String role;

    /** 删除 0显示  1删除 */
    @Excel(name = "删除 0显示  1删除")
    private Integer status;

}
