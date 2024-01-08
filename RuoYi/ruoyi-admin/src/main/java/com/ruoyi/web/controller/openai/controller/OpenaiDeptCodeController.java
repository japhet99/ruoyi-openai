package com.ruoyi.web.controller.openai.controller;

import java.util.List;

import com.ruoyi.openai.domain.OpenaiDeptCode;
import com.ruoyi.openai.service.IOpenaiDeptCodeService;
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
 * 用户邀请码Controller
 * 
 * @author ruoyi
 * @date 2023-06-01
 */
@Controller
@RequestMapping("/system/code")
public class OpenaiDeptCodeController extends BaseController
{
    private String prefix = "openai/code";

    @Autowired
    private IOpenaiDeptCodeService openaiDeptCodeService;

    @RequiresPermissions("system:code:view")
    @GetMapping()
    public String code()
    {
        return prefix + "/code";
    }

    /**
     * 查询用户邀请码列表
     */
    @RequiresPermissions("system:code:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OpenaiDeptCode openaiDeptCode)
    {
        startPage();
        List<OpenaiDeptCode> list = openaiDeptCodeService.selectOpenaiDeptCodeList(openaiDeptCode);
        return getDataTable(list);
    }

    /**
     * 导出用户邀请码列表
     */
    @RequiresPermissions("system:code:export")
    @Log(title = "用户邀请码", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OpenaiDeptCode openaiDeptCode)
    {
        List<OpenaiDeptCode> list = openaiDeptCodeService.selectOpenaiDeptCodeList(openaiDeptCode);
        ExcelUtil<OpenaiDeptCode> util = new ExcelUtil<OpenaiDeptCode>(OpenaiDeptCode.class);
        return util.exportExcel(list, "用户邀请码数据");
    }

    /**
     * 新增用户邀请码
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存用户邀请码
     */
    @RequiresPermissions("system:code:add")
    @Log(title = "用户邀请码", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OpenaiDeptCode openaiDeptCode)
    {
        return toAjax(openaiDeptCodeService.insertOpenaiDeptCode(openaiDeptCode));
    }

    /**
     * 修改用户邀请码
     */
    @RequiresPermissions("system:code:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        OpenaiDeptCode openaiDeptCode = openaiDeptCodeService.selectOpenaiDeptCodeById(id);
        mmap.put("openaiDeptCode", openaiDeptCode);
        return prefix + "/edit";
    }

    /**
     * 修改保存用户邀请码
     */
    @RequiresPermissions("system:code:edit")
    @Log(title = "用户邀请码", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OpenaiDeptCode openaiDeptCode)
    {
        return toAjax(openaiDeptCodeService.updateOpenaiDeptCode(openaiDeptCode));
    }

    /**
     * 删除用户邀请码
     */
    @RequiresPermissions("system:code:remove")
    @Log(title = "用户邀请码", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(openaiDeptCodeService.deleteOpenaiDeptCodeByIds(ids));
    }
}
