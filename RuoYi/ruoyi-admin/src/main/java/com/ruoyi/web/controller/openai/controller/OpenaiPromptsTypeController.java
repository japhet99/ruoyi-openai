package com.ruoyi.web.controller.openai.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.openai.domain.OpenaiPromptsType;
import com.ruoyi.openai.service.IOpenaiPromptsTypeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 类型Controller
 * 
 * @author ruoyi
 * @date 2023-07-06
 */
@Controller
@RequestMapping("/system/type")
public class OpenaiPromptsTypeController extends BaseController
{
    private String prefix = "openai/type";

    @Autowired
    private IOpenaiPromptsTypeService openaiPromptsTypeService;

    @RequiresPermissions("system:type:view")
    @GetMapping()
    public String type()
    {
        return prefix + "/type";
    }

    /**
     * 查询类型列表
     */
    @RequiresPermissions("system:type:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OpenaiPromptsType openaiPromptsType)
    {
        startPage();
        List<OpenaiPromptsType> list = openaiPromptsTypeService.selectOpenaiPromptsTypeList(openaiPromptsType);
        return getDataTable(list);
    }

    /**
     * 导出类型列表
     */
    @RequiresPermissions("system:type:export")
    @Log(title = "类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OpenaiPromptsType openaiPromptsType)
    {
        List<OpenaiPromptsType> list = openaiPromptsTypeService.selectOpenaiPromptsTypeList(openaiPromptsType);
        ExcelUtil<OpenaiPromptsType> util = new ExcelUtil<OpenaiPromptsType>(OpenaiPromptsType.class);
        return util.exportExcel(list, "类型数据");
    }

    /**
     * 新增类型
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存类型
     */
    @RequiresPermissions("system:type:add")
    @Log(title = "类型", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OpenaiPromptsType openaiPromptsType)
    {
        return toAjax(openaiPromptsTypeService.insertOpenaiPromptsType(openaiPromptsType));
    }

    /**
     * 修改类型
     */
    @RequiresPermissions("system:type:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        OpenaiPromptsType openaiPromptsType = openaiPromptsTypeService.selectOpenaiPromptsTypeById(id);
        mmap.put("openaiPromptsType", openaiPromptsType);
        return prefix + "/edit";
    }

    /**
     * 修改保存类型
     */
    @RequiresPermissions("system:type:edit")
    @Log(title = "类型", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OpenaiPromptsType openaiPromptsType)
    {
        return toAjax(openaiPromptsTypeService.updateOpenaiPromptsType(openaiPromptsType));
    }

    /**
     * 删除类型
     */
    @RequiresPermissions("system:type:remove")
    @Log(title = "类型", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(openaiPromptsTypeService.deleteOpenaiPromptsTypeByIds(ids));
    }
}
