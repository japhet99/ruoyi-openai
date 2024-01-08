package com.ruoyi.web.controller.openai.controller;

import java.util.List;

import com.ruoyi.openai.domain.OpenaiUserInfo;
import com.ruoyi.openai.service.IOpenaiUserInfoService;
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
 * 用户信息详情Controller
 * 
 * @author ruoyi
 * @date 2023-05-23
 */
@Controller
@RequestMapping("/system/info")
public class OpenaiUserInfoController extends BaseController
{
    private String prefix = "openai/info";

    @Autowired
    private IOpenaiUserInfoService openaiUserInfoService;

    @RequiresPermissions("system:info:view")
    @GetMapping()
    public String info(ModelMap mmap)
    {
        mmap.put("userId", "");
        return prefix + "/info";
    }

    /**
     * 查询用户信息详情列表
     */
    @RequiresPermissions("system:info:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OpenaiUserInfo openaiUserInfo)
    {
        startPage();
        List<OpenaiUserInfo> list = openaiUserInfoService.selectOpenaiUserInfoList(openaiUserInfo);
        return getDataTable(list);
    }

    /**
     * 导出用户信息详情列表
     */
    @RequiresPermissions("system:info:export")
    @Log(title = "用户信息详情", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OpenaiUserInfo openaiUserInfo)
    {
        List<OpenaiUserInfo> list = openaiUserInfoService.selectOpenaiUserInfoList(openaiUserInfo);
        ExcelUtil<OpenaiUserInfo> util = new ExcelUtil<OpenaiUserInfo>(OpenaiUserInfo.class);
        return util.exportExcel(list, "用户信息详情数据");
    }

    /**
     * 新增用户信息详情
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存用户信息详情
     */
    @RequiresPermissions("system:info:add")
    @Log(title = "用户信息详情", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OpenaiUserInfo openaiUserInfo)
    {
        return toAjax(openaiUserInfoService.insertOpenaiUserInfo(openaiUserInfo));
    }

    /**
     * 修改用户信息详情
     */
    @RequiresPermissions("system:info:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        OpenaiUserInfo openaiUserInfo = openaiUserInfoService.selectOpenaiUserInfoById(id);
        mmap.put("openaiUserInfo", openaiUserInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存用户信息详情
     */
    @RequiresPermissions("system:info:edit")
    @Log(title = "用户信息详情", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OpenaiUserInfo openaiUserInfo)
    {
        return toAjax(openaiUserInfoService.updateOpenaiUserInfo(openaiUserInfo));
    }

    /**
     * 删除用户信息详情
     */
    @RequiresPermissions("system:info:remove")
    @Log(title = "用户信息详情", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(openaiUserInfoService.deleteOpenaiUserInfoByIds(ids));
    }


    @RequiresPermissions("system:detail:view")
    @GetMapping("/infoByUserId/{userId}")
    public String detailByUserId(@PathVariable("userId") Long userId, ModelMap mmap)
    {
        mmap.put("userId", userId);
        return prefix + "/info";
    }

}
