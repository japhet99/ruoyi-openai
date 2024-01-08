package com.ruoyi.web.controller.openai.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.openai.domain.OpenaiChatPlan;
import com.ruoyi.openai.service.IOpenaiChatPlanService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 套餐计划Controller
 * 
 * @author ruoyi
 * @date 2023-07-04
 */
@Controller
@RequestMapping("/system/plan")
public class OpenaiChatPlanController extends BaseController
{
    private String prefix = "openai/plan";

    @Autowired
    private IOpenaiChatPlanService openaiChatPlanService;

    @RequiresPermissions("system:plan:view")
    @GetMapping()
    public String plan()
    {
        return prefix + "/plan";
    }

    /**
     * 查询套餐计划列表
     */
    @RequiresPermissions("system:plan:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OpenaiChatPlan openaiChatPlan)
    {
        startPage();
        List<OpenaiChatPlan> list = openaiChatPlanService.selectOpenaiChatPlanList(openaiChatPlan);
        return getDataTable(list);
    }

    /**
     * 导出套餐计划列表
     */
    @RequiresPermissions("system:plan:export")
    @Log(title = "套餐计划", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OpenaiChatPlan openaiChatPlan)
    {
        List<OpenaiChatPlan> list = openaiChatPlanService.selectOpenaiChatPlanList(openaiChatPlan);
        ExcelUtil<OpenaiChatPlan> util = new ExcelUtil<OpenaiChatPlan>(OpenaiChatPlan.class);
        return util.exportExcel(list, "套餐计划数据");
    }

    /**
     * 新增套餐计划
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存套餐计划
     */
    @RequiresPermissions("system:plan:add")
    @Log(title = "套餐计划", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OpenaiChatPlan openaiChatPlan)
    {
        return toAjax(openaiChatPlanService.insertOpenaiChatPlan(openaiChatPlan));
    }

    /**
     * 修改套餐计划
     */
    @RequiresPermissions("system:plan:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        OpenaiChatPlan openaiChatPlan = openaiChatPlanService.selectOpenaiChatPlanById(id);
        mmap.put("openaiChatPlan", openaiChatPlan);
        return prefix + "/edit";
    }

    /**
     * 修改保存套餐计划
     */
    @RequiresPermissions("system:plan:edit")
    @Log(title = "套餐计划", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OpenaiChatPlan openaiChatPlan)
    {
        return toAjax(openaiChatPlanService.updateOpenaiChatPlan(openaiChatPlan));
    }

    /**
     * 删除套餐计划
     */
    @RequiresPermissions("system:plan:remove")
    @Log(title = "套餐计划", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(openaiChatPlanService.deleteOpenaiChatPlanByIds(ids));
    }
}
