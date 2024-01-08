package com.ruoyi.web.controller.openai.controller;

import java.util.List;

import com.ruoyi.openai.domain.OpenaiFaq;
import com.ruoyi.openai.service.IOpenaiFaqService;
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
 * FAQ 常用问题解答Controller
 * 
 * @author ruoyi
 * @date 2023-05-23
 */
@Controller
@RequestMapping("/system/faq")
public class OpenaiFaqController extends BaseController
{
    private String prefix = "openai/faq";

    @Autowired
    private IOpenaiFaqService openaiFaqService;

    @RequiresPermissions("system:faq:view")
    @GetMapping()
    public String faq()
    {
        return prefix + "/faq";
    }

    /**
     * 查询FAQ 常用问题解答列表
     */
    @RequiresPermissions("system:faq:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OpenaiFaq openaiFaq)
    {
        startPage();
        List<OpenaiFaq> list = openaiFaqService.selectOpenaiFaqList(openaiFaq);
        return getDataTable(list);
    }

    /**
     * 导出FAQ 常用问题解答列表
     */
    @RequiresPermissions("system:faq:export")
    @Log(title = "FAQ 常用问题解答", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OpenaiFaq openaiFaq)
    {
        List<OpenaiFaq> list = openaiFaqService.selectOpenaiFaqList(openaiFaq);
        ExcelUtil<OpenaiFaq> util = new ExcelUtil<OpenaiFaq>(OpenaiFaq.class);
        return util.exportExcel(list, "FAQ 常用问题解答数据");
    }

    /**
     * 新增FAQ 常用问题解答
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存FAQ 常用问题解答
     */
    @RequiresPermissions("system:faq:add")
    @Log(title = "FAQ 常用问题解答", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OpenaiFaq openaiFaq)
    {
        return toAjax(openaiFaqService.insertOpenaiFaq(openaiFaq));
    }

    /**
     * 修改FAQ 常用问题解答
     */
    @RequiresPermissions("system:faq:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        OpenaiFaq openaiFaq = openaiFaqService.selectOpenaiFaqById(id);
        mmap.put("openaiFaq", openaiFaq);
        return prefix + "/edit";
    }

    /**
     * 修改保存FAQ 常用问题解答
     */
    @RequiresPermissions("system:faq:edit")
    @Log(title = "FAQ 常用问题解答", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OpenaiFaq openaiFaq)
    {
        return toAjax(openaiFaqService.updateOpenaiFaq(openaiFaq));
    }

    /**
     * 删除FAQ 常用问题解答
     */
    @RequiresPermissions("system:faq:remove")
    @Log(title = "FAQ 常用问题解答", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(openaiFaqService.deleteOpenaiFaqByIds(ids));
    }
}
