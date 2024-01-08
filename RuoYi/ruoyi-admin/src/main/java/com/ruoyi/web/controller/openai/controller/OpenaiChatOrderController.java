package com.ruoyi.web.controller.openai.controller;

import com.github.pagehelper.PageHelper;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.openai.domain.OpenaiChatOrder;
import com.ruoyi.openai.domain.OpenaiChatPlan;
import com.ruoyi.openai.service.IOpenaiChatOrderService;
import com.ruoyi.openai.service.IOpenaiChatPlanService;
import com.ruoyi.system.service.ISysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户订单Controller
 * 
 * @author ruoyi
 * @date 2023-07-04
 */
@Controller
@RequestMapping("/system/order")
public class OpenaiChatOrderController extends BaseController
{
    private String prefix = "openai/order";

    @Autowired
    private IOpenaiChatOrderService openaiChatOrderService;
    @Autowired
    private IOpenaiChatPlanService openaiChatPlanService;
    @Autowired
    private ISysUserService userService;

    @RequiresPermissions("system:order:view")
    @GetMapping()
    public String order()
    {
        return prefix + "/order";
    }

    /**
     * 查询用户订单列表
     */
    @RequiresPermissions("system:order:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OpenaiChatOrder openaiChatOrder)
    {
        startPage();
        List<OpenaiChatOrder> list = openaiChatOrderService.selectOpenaiChatOrderList(openaiChatOrder);
        for (OpenaiChatOrder chatOrder : list) {
            OpenaiChatPlan openaiChatPlan = openaiChatPlanService.selectOpenaiChatPlanById(chatOrder.getPlanId());
            SysUser sysUser = userService.selectUserById(chatOrder.getUserId());
            chatOrder.setPlanName(openaiChatPlan.getName());
            chatOrder.setUserName(sysUser.getUserName());
        }
        return getDataTable(list);
    }

    /**
     * 导出用户订单列表
     */
    @RequiresPermissions("system:order:export")
    @Log(title = "用户订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OpenaiChatOrder openaiChatOrder)
    {
        List<OpenaiChatOrder> list = openaiChatOrderService.selectOpenaiChatOrderList(openaiChatOrder);
        ExcelUtil<OpenaiChatOrder> util = new ExcelUtil<OpenaiChatOrder>(OpenaiChatOrder.class);
        return util.exportExcel(list, "用户订单数据");
    }

    /**
     * 新增用户订单
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存用户订单
     */
    @RequiresPermissions("system:order:add")
    @Log(title = "用户订单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OpenaiChatOrder openaiChatOrder)
    {
        return toAjax(openaiChatOrderService.insertOpenaiChatOrder(openaiChatOrder));
    }

    /**
     * 修改用户订单
     */
    @RequiresPermissions("system:order:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        OpenaiChatOrder openaiChatOrder = openaiChatOrderService.selectOpenaiChatOrderById(id);
        mmap.put("openaiChatOrder", openaiChatOrder);
        return prefix + "/edit";
    }

    /**
     * 修改保存用户订单
     */
    @RequiresPermissions("system:order:edit")
    @Log(title = "用户订单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OpenaiChatOrder openaiChatOrder)
    {
        return toAjax(openaiChatOrderService.updateOpenaiChatOrder(openaiChatOrder));
    }

    /**
     * 删除用户订单
     */
    @RequiresPermissions("system:order:remove")
    @Log(title = "用户订单", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(openaiChatOrderService.deleteOpenaiChatOrderByIds(ids));
    }
}
