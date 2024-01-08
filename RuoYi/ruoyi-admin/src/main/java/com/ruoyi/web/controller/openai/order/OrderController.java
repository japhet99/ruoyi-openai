package com.ruoyi.web.controller.openai.order;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.QRCodeUtil;
import com.ruoyi.openai.service.IOpenaiChatOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {


    @Autowired
    private IOpenaiChatOrderService openaiChatOrderService;

    /**
     * 创建订单
     * @return
     */
    @PostMapping("/createOrder")
    @ResponseBody
    public AjaxResult createOrder(Long pId) {

        SysUser sysUser = getSysUser();
        if (sysUser == null) {
            return AjaxResult.error("服务器超时，请重新登录");
        }
        Long userId = sysUser.getUserId();
        return openaiChatOrderService.createOrder(userId,pId);
    }

}
