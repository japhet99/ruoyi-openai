package com.ruoyi.openai.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户订单对象 openai_chat_order
 * 
 * @author ruoyi
 * @date 2023-07-04
 */
@Data
public class OpenaiChatOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** companyId */
    private Long companyId;

    /** user_id  */
    @Excel(name = "user_id")
    private Long userId;
    private String userName;

    /** order_id 云端订单号，可用于查询订单是否支付成功*/
    @Excel(name = "order_id")
    private String orderId;

    /** 计划id */
    @Excel(name = "plan_id")
    private Long planId;
    private String planName;

    /** 订单id */
    @Excel(name = "订单id")
    private String payId;

    /** 支付类型 1:微信  2:支付宝 */
    @Excel(name = "支付类型 1:微信  2:支付宝")
    private Integer type;

    /** 订单金额 */
    @Excel(name = "订单金额")
    private BigDecimal orderPrice;

    /** 支付金额 */
    @Excel(name = "支付金额")
    private BigDecimal payPrice;

    /** 状态 0:待支付  1:完成   2:过期  3:关闭 */
    @Excel(name = "状态 0:待支付  1:完成   2:过期 3:关闭")
    private Integer status;

    /** 支付时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payTime;

}
