package com.ruoyi.openai.domain;

import java.math.BigDecimal;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 账户日志对象 openai_user_account_log
 * 
 * @author ruoyi
 * @date 2023-08-09
 */
@Data
public class OpenaiUserAccountLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** companyId */
    private Long companyId;

    /** userId */
    @Excel(name = "userId")
    private Long userId;

    /** 类型 0:注册 1:充值 2:提现 3:兑换 */
    @Excel(name = "类型 0:注册 1:充值 2:提现 3:兑换")
    private Integer type;

    /** 金额 */
    @Excel(name = "金额")
    private BigDecimal money;


}
