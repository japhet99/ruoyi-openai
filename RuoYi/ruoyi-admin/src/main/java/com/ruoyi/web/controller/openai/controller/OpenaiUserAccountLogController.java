package com.ruoyi.web.controller.openai.controller;

import java.util.List;

import com.ruoyi.openai.domain.OpenaiUserAccountLog;
import com.ruoyi.openai.service.IOpenaiUserAccountLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 账户日志Controller
 * 
 * @author ruoyi
 * @date 2023-08-09
 */
@Controller
@RequestMapping("/system/accountLog")
public class OpenaiUserAccountLogController extends BaseController
{
    private String prefix = "openai/accountLog";

    @Autowired
    private IOpenaiUserAccountLogService openaiUserAccountLogService;

    @RequiresPermissions("system:accountLog:view")
    @GetMapping()
    public String log()
    {
        return prefix + "/log";
    }

    /**
     * 查询账户日志列表
     */
    @RequiresPermissions("system:accountLog:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OpenaiUserAccountLog openaiUserAccountLog)
    {
        startPage();
        List<OpenaiUserAccountLog> list = openaiUserAccountLogService.selectOpenaiUserAccountLogList(openaiUserAccountLog);
        return getDataTable(list);
    }

    /**
     * 导出账户日志列表
     */
    @RequiresPermissions("system:accountLog:export")
    @Log(title = "账户日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OpenaiUserAccountLog openaiUserAccountLog)
    {
        List<OpenaiUserAccountLog> list = openaiUserAccountLogService.selectOpenaiUserAccountLogList(openaiUserAccountLog);
        ExcelUtil<OpenaiUserAccountLog> util = new ExcelUtil<OpenaiUserAccountLog>(OpenaiUserAccountLog.class);
        return util.exportExcel(list, "账户日志数据");
    }

    /**
     * 新增账户日志
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存账户日志
     */
    @RequiresPermissions("system:accountLog:add")
    @Log(title = "账户日志", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OpenaiUserAccountLog openaiUserAccountLog)
    {
        return toAjax(openaiUserAccountLogService.insertOpenaiUserAccountLog(openaiUserAccountLog));
    }

    /**
     * 修改账户日志
     */
    @RequiresPermissions("system:accountLog:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        OpenaiUserAccountLog openaiUserAccountLog = openaiUserAccountLogService.selectOpenaiUserAccountLogById(id);
        mmap.put("openaiUserAccountLog", openaiUserAccountLog);
        return prefix + "/edit";
    }

    /**
     * 修改保存账户日志
     */
    @RequiresPermissions("system:accountLog:edit")
    @Log(title = "账户日志", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OpenaiUserAccountLog openaiUserAccountLog)
    {
        return toAjax(openaiUserAccountLogService.updateOpenaiUserAccountLog(openaiUserAccountLog));
    }

    /**
     * 删除账户日志
     */
    @RequiresPermissions("system:accountLog:remove")
    @Log(title = "账户日志", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(openaiUserAccountLogService.deleteOpenaiUserAccountLogByIds(ids));
    }
}
