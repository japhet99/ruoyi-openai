package com.ruoyi.web.controller.openai.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.openai.domain.OpenaiPrompts;
import com.ruoyi.openai.service.IOpenaiPromptsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * promptsController
 * 
 * @author ruoyi
 * @date 2023-06-30
 */
@Controller
@RequestMapping("/system/prompts")
public class OpenaiPromptsController extends BaseController
{
    private String prefix = "openai/prompts";

    @Autowired
    private IOpenaiPromptsService openaiPromptsService;

    @RequiresPermissions("system:prompts:view")
    @GetMapping()
    public String prompts()
    {
        return prefix + "/prompts";
    }

    /**
     * 查询prompts列表
     */
    @RequiresPermissions("system:prompts:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OpenaiPrompts openaiPrompts)
    {
        startPage();
        List<OpenaiPrompts> list = openaiPromptsService.selectOpenaiPromptsList(openaiPrompts);
        return getDataTable(list);
    }

    /**
     * 导出prompts列表
     */
    @RequiresPermissions("system:prompts:export")
    @Log(title = "prompts", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OpenaiPrompts openaiPrompts)
    {
        List<OpenaiPrompts> list = openaiPromptsService.selectOpenaiPromptsList(openaiPrompts);
        ExcelUtil<OpenaiPrompts> util = new ExcelUtil<OpenaiPrompts>(OpenaiPrompts.class);
        return util.exportExcel(list, "prompts数据");
    }

    /**
     * 新增prompts
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存prompts
     */
    @RequiresPermissions("system:prompts:add")
    @Log(title = "prompts", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OpenaiPrompts openaiPrompts)
    {
        return toAjax(openaiPromptsService.insertOpenaiPrompts(openaiPrompts));
    }

    /**
     * 修改prompts
     */
    @RequiresPermissions("system:prompts:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        OpenaiPrompts openaiPrompts = openaiPromptsService.selectOpenaiPromptsById(id);
        mmap.put("openaiPrompts", openaiPrompts);
        return prefix + "/edit";
    }

    /**
     * 修改保存prompts
     */
    @RequiresPermissions("system:prompts:edit")
    @Log(title = "prompts", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OpenaiPrompts openaiPrompts)
    {
        return toAjax(openaiPromptsService.updateOpenaiPrompts(openaiPrompts));
    }

    /**
     * 删除prompts
     */
    @RequiresPermissions("system:prompts:remove")
    @Log(title = "prompts", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(openaiPromptsService.deleteOpenaiPromptsByIds(ids));
    }
}
