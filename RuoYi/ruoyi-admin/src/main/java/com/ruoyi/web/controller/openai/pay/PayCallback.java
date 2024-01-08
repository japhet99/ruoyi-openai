package com.ruoyi.web.controller.openai.pay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * 支付回调
 */
@RestController
@RequestMapping("/pay")
public class PayCallback {

    private static final Logger log = LoggerFactory.getLogger(PayCallback.class);


    /**
     * 支付完成回调
     * @param payId	字符串	商户订单号
     * @param param	字符串	创建订单的时候传入的参数
     * @param type	整数	支付方式 ：微信支付为1 支付宝支付为2
     * @param price	小数	订单金额
     * @param reallyPrice	小数	实际支付金额
     * @param sign	字符串	校验签名，计算方式 = md5(payId + param + type + price + reallyPrice + 通讯密钥)
     */
    @GetMapping("/callback")
    public String signOk(String payId, String param, Integer type, BigDecimal price,BigDecimal reallyPrice,String sign){
        log.info("---------------------------------------------------------------------");
        log.info("----------------------------完成回调-----------------------------------------");
        log.info("----------------------------"+payId+"-----------------------------------------");
        log.info("----------------------------"+param+"-----------------------------------------");
        log.info("----------------------------"+type+"-----------------------------------------");
        log.info("----------------------------"+price+"-----------------------------------------");
        log.info("----------------------------"+reallyPrice+"-----------------------------------------");
        log.info("----------------------------"+sign+"-----------------------------------------");
        log.info("---------------------------------------------------------------------");

        // todo 自己业务

        return "success";
    }


}
