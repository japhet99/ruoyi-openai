package com.ruoyi.web.controller.openai.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.openai.domain.OpenaiOrderLog;
import com.ruoyi.openai.service.IOpenaiOrderLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 支付成功回调Controller
 * 
 * @author ruoyi
 * @date 2023-07-05
 */
@Controller
@RequestMapping("/system/log")
public class OpenaiOrderLogController extends BaseController
{
    private String prefix = "openai/log";

    @Autowired
    private IOpenaiOrderLogService openaiOrderLogService;

    @RequiresPermissions("system:log:view")
    @GetMapping()
    public String log()
    {
        return prefix + "/log";
    }

    /**
     * 查询支付成功回调列表
     */
    @RequiresPermissions("system:log:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OpenaiOrderLog openaiOrderLog)
    {
        startPage();
        List<OpenaiOrderLog> list = openaiOrderLogService.selectOpenaiOrderLogList(openaiOrderLog);
        return getDataTable(list);
    }

    /**
     * 导出支付成功回调列表
     */
    @RequiresPermissions("system:log:export")
    @Log(title = "支付成功回调", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OpenaiOrderLog openaiOrderLog)
    {
        List<OpenaiOrderLog> list = openaiOrderLogService.selectOpenaiOrderLogList(openaiOrderLog);
        ExcelUtil<OpenaiOrderLog> util = new ExcelUtil<OpenaiOrderLog>(OpenaiOrderLog.class);
        return util.exportExcel(list, "支付成功回调数据");
    }

    /**
     * 新增支付成功回调
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存支付成功回调
     */
    @RequiresPermissions("system:log:add")
    @Log(title = "支付成功回调", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OpenaiOrderLog openaiOrderLog)
    {
        return toAjax(openaiOrderLogService.insertOpenaiOrderLog(openaiOrderLog));
    }

    /**
     * 修改支付成功回调
     */
    @RequiresPermissions("system:log:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        OpenaiOrderLog openaiOrderLog = openaiOrderLogService.selectOpenaiOrderLogById(id);
        mmap.put("openaiOrderLog", openaiOrderLog);
        return prefix + "/edit";
    }

    /**
     * 修改保存支付成功回调
     */
    @RequiresPermissions("system:log:edit")
    @Log(title = "支付成功回调", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OpenaiOrderLog openaiOrderLog)
    {
        return toAjax(openaiOrderLogService.updateOpenaiOrderLog(openaiOrderLog));
    }

    /**
     * 删除支付成功回调
     */
    @RequiresPermissions("system:log:remove")
    @Log(title = "支付成功回调", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(openaiOrderLogService.deleteOpenaiOrderLogByIds(ids));
    }
}
