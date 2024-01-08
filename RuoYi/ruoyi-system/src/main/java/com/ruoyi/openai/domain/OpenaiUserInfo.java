package com.ruoyi.openai.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户信息详情对象 openai_user_info
 * 
 * @author ruoyi
 * @date 2023-05-23
 */
@Data
public class OpenaiUserInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;
    /** companyId */
    private Long companyId;
    /** 每天免费次数 */
    @Excel(name = "每天免费次数")
    private Long freeTime;

    /** 充值后的剩余次数 */
    @Excel(name = "充值后的剩余次数")
    private Long moneyTime;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 充值时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "充值时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date rechargeTime;

    /** 到期时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "到期时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date expirationTime;

    /** 充值后的剩余次数 */
    @Excel(name = "充值后的剩余次数")
    private Long moneyTimeFour;

    /** 充值时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "充值时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date rechargeTimeFour;

    /** 到期时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "到期时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date expirationTimeFour;

    /** 每天免费次数 */
    @Excel(name = "每天免费次数")
    private Long mjFreeTime;

    /** 充值后的剩余次数 */
    @Excel(name = "充值后的剩余次数")
    private Long mjMoneyTime;

    /** mj充值时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "mj充值时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date rechargeTimeMj;


    /** 充值后的剩余次数 */
    @Excel(name = "充值后的剩余次数")
    private Long dallETime;

    /** DallE充值时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "DallE充值时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date rechargeTimeDallE;

    /** 邀请码 */
    @Excel(name = "邀请码")
    private String code;
    /** ip */
    @Excel(name = "ip")
    private String ip;

    /** 可提现金额 */
    @Excel(name = "可提现金额")
    private BigDecimal money;
    /** 冻结金额 */
    @Excel(name = "冻结金额")
    private BigDecimal freezeMoney;
    /** 总金额(可提现金额 + 冻结金额) */
    @Excel(name = "总金额(可提现金额 + 冻结金额)")
    private BigDecimal totalMoney;
    /** 订单首次购买次数 */
    @Excel(name = "订单首次购买次数")
    private int buyCount;
    /** 邀请人数 */
    @Excel(name = "邀请人数")
    private int inviteCount;
    /** 公告已读未读 0:未读  1:已读 */
    @Excel(name = "公告已读未读 0:未读  1:已读")
    private int read;
}
