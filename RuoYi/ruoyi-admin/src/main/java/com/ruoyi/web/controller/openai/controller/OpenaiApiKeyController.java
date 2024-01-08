package com.ruoyi.web.controller.openai.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.openai.domain.OpenaiApiKey;
import com.ruoyi.openai.service.IOpenaiApiKeyService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * api_key 列Controller
 * 
 * @author ruoyi
 * @date 2023-07-10
 */
@Controller
@RequestMapping("/system/key")
public class OpenaiApiKeyController extends BaseController
{
    private String prefix = "system/key";

    @Autowired
    private IOpenaiApiKeyService openaiApiKeyService;

    @RequiresPermissions("system:key:view")
    @GetMapping()
    public String key()
    {
        return prefix + "/key";
    }

    /**
     * 查询api_key 列列表
     */
    @RequiresPermissions("system:key:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OpenaiApiKey openaiApiKey)
    {
        startPage();
        List<OpenaiApiKey> list = openaiApiKeyService.selectOpenaiApiKeyList(openaiApiKey);
        return getDataTable(list);
    }

    /**
     * 导出api_key 列列表
     */
    @RequiresPermissions("system:key:export")
    @Log(title = "api_key 列", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OpenaiApiKey openaiApiKey)
    {
        List<OpenaiApiKey> list = openaiApiKeyService.selectOpenaiApiKeyList(openaiApiKey);
        ExcelUtil<OpenaiApiKey> util = new ExcelUtil<OpenaiApiKey>(OpenaiApiKey.class);
        return util.exportExcel(list, "api_key 列数据");
    }

    /**
     * 新增api_key 列
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存api_key 列
     */
    @RequiresPermissions("system:key:add")
    @Log(title = "api_key 列", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OpenaiApiKey openaiApiKey)
    {
        return toAjax(openaiApiKeyService.insertOpenaiApiKey(openaiApiKey));
    }

    /**
     * 修改api_key 列
     */
    @RequiresPermissions("system:key:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        OpenaiApiKey openaiApiKey = openaiApiKeyService.selectOpenaiApiKeyById(id);
        mmap.put("openaiApiKey", openaiApiKey);
        return prefix + "/edit";
    }

    /**
     * 修改保存api_key 列
     */
    @RequiresPermissions("system:key:edit")
    @Log(title = "api_key 列", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OpenaiApiKey openaiApiKey)
    {
        return toAjax(openaiApiKeyService.updateOpenaiApiKey(openaiApiKey));
    }

    /**
     * 删除api_key 列
     */
    @RequiresPermissions("system:key:remove")
    @Log(title = "api_key 列", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(openaiApiKeyService.deleteOpenaiApiKeyByIds(ids));
    }
}
