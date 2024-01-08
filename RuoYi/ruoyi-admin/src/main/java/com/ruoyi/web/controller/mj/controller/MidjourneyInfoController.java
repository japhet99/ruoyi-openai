package com.ruoyi.web.controller.mj.controller;

import java.util.List;

import com.ruoyi.mj.domain.MjTaskInfo;
import com.ruoyi.mj.service.IMjTaskInfoService;
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
 * midjourney 绘画任务Controller
 * 
 * @author ruoyi
 * @date 2023-09-21
 */
@Controller
@RequestMapping("/system/mj")
public class MidjourneyInfoController extends BaseController
{
    private String prefix = "mj/info";

    @Autowired
    private IMjTaskInfoService mjTaskInfoService;

    @RequiresPermissions("system:info:view")
    @GetMapping()
    public String info()
    {
        return prefix + "/info";
    }

    /**
     * 查询midjourney 绘画任务列表
     */
    @RequiresPermissions("system:info:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(MjTaskInfo mjTaskInfo)
    {
        startPage();
        List<MjTaskInfo> list = mjTaskInfoService.selectMjTaskInfoList(mjTaskInfo);
        return getDataTable(list);
    }

    /**
     * 导出midjourney 绘画任务列表
     */
    @RequiresPermissions("system:info:export")
    @Log(title = "midjourney 绘画任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MjTaskInfo mjTaskInfo)
    {
        List<MjTaskInfo> list = mjTaskInfoService.selectMjTaskInfoList(mjTaskInfo);
        ExcelUtil<MjTaskInfo> util = new ExcelUtil<MjTaskInfo>(MjTaskInfo.class);
        return util.exportExcel(list, "midjourney 绘画任务数据");
    }

    /**
     * 新增midjourney 绘画任务
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存midjourney 绘画任务
     */
    @RequiresPermissions("system:info:add")
    @Log(title = "midjourney 绘画任务", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(MjTaskInfo mjTaskInfo)
    {
        return toAjax(mjTaskInfoService.insertMjTaskInfo(mjTaskInfo));
    }

    /**
     * 修改midjourney 绘画任务
     */
    @RequiresPermissions("system:info:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        MjTaskInfo mjTaskInfo = mjTaskInfoService.selectMjTaskInfoById(id);
        mmap.put("mjTaskInfo", mjTaskInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存midjourney 绘画任务
     */
    @RequiresPermissions("system:info:edit")
    @Log(title = "midjourney 绘画任务", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(MjTaskInfo mjTaskInfo)
    {
        return toAjax(mjTaskInfoService.updateMjTaskInfo(mjTaskInfo));
    }

    /**
     * 删除midjourney 绘画任务
     */
    @RequiresPermissions("system:info:remove")
    @Log(title = "midjourney 绘画任务", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(mjTaskInfoService.deleteMjTaskInfoByIds(ids));
    }
}
