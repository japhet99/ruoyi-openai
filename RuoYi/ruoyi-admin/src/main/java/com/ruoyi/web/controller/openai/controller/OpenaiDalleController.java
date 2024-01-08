package com.ruoyi.web.controller.openai.controller;

import java.util.List;

import com.ruoyi.openai.domain.OpenaiDalle;
import com.ruoyi.openai.service.IOpenaiDalleService;
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
 * DALL.EController
 * 
 * @author ruoyi
 * @date 2023-11-27
 */
@Controller
@RequestMapping("/system/dalle")
public class OpenaiDalleController extends BaseController
{
    private String prefix = "mj/dalle";

    @Autowired
    private IOpenaiDalleService openaiDalleService;

    @RequiresPermissions("system:dalle:view")
    @GetMapping()
    public String dalle()
    {
        return prefix + "/dalle";
    }

    /**
     * 查询DALL.E列表
     */
    @RequiresPermissions("system:dalle:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OpenaiDalle openaiDalle)
    {
        startPage();
        List<OpenaiDalle> list = openaiDalleService.selectOpenaiDalleList(openaiDalle);
        return getDataTable(list);
    }

    /**
     * 导出DALL.E列表
     */
    @RequiresPermissions("system:dalle:export")
    @Log(title = "DALL.E", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OpenaiDalle openaiDalle)
    {
        List<OpenaiDalle> list = openaiDalleService.selectOpenaiDalleList(openaiDalle);
        ExcelUtil<OpenaiDalle> util = new ExcelUtil<OpenaiDalle>(OpenaiDalle.class);
        return util.exportExcel(list, "DALL.E数据");
    }

    /**
     * 新增DALL.E
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存DALL.E
     */
    @RequiresPermissions("system:dalle:add")
    @Log(title = "DALL.E", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OpenaiDalle openaiDalle)
    {
        return toAjax(openaiDalleService.insertOpenaiDalle(openaiDalle));
    }

    /**
     * 修改DALL.E
     */
    @RequiresPermissions("system:dalle:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        OpenaiDalle openaiDalle = openaiDalleService.selectOpenaiDalleById(id);
        mmap.put("openaiDalle", openaiDalle);
        return prefix + "/edit";
    }

    /**
     * 修改保存DALL.E
     */
    @RequiresPermissions("system:dalle:edit")
    @Log(title = "DALL.E", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OpenaiDalle openaiDalle)
    {
        return toAjax(openaiDalleService.updateOpenaiDalle(openaiDalle));
    }

    /**
     * 删除DALL.E
     */
    @RequiresPermissions("system:dalle:remove")
    @Log(title = "DALL.E", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(openaiDalleService.deleteOpenaiDalleByIds(ids));
    }
}
