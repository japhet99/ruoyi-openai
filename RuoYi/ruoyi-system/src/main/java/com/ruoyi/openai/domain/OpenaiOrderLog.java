package com.ruoyi.openai.domain;

import java.math.BigDecimal;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 支付成功回调对象 openai_order_log
 * 
 * @author ruoyi
 * @date 2023-07-05
 */
@Data
public class OpenaiOrderLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** companyId */
    private Long companyId;

    /** userId */
    @Excel(name = "userId")
    private Long userId;

    /** 类型 0: 按天  1:次 */
    @Excel(name = "类型 0: 按天  1:次")
    private Integer type;

    /** 订单id */
    @Excel(name = "订单id")
    private String payId;

    /** 支付方式 ：微信支付为1 支付宝支付为2 */
    @Excel(name = "支付方式 ：微信支付为1 支付宝支付为2")
    private Integer payType;

    /** 订单金额 */
    @Excel(name = "订单金额")
    private BigDecimal price;

    /** 实际支付金额 */
    @Excel(name = "实际支付金额")
    private BigDecimal reallyPrice;

    /** 签名 */
    @Excel(name = "签名")
    private String sign;

}
