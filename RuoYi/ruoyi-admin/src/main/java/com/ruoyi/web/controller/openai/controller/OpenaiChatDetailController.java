package com.ruoyi.web.controller.openai.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.openai.domain.OpenaiChatDetail;
import com.ruoyi.openai.domain.OpenaiPrompts;
import com.ruoyi.openai.service.IOpenaiChatDetailService;
import com.ruoyi.openai.service.IOpenaiPromptsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 聊天记录详情Controller
 * 
 * @author ruoyi
 * @date 2023-05-23
 */
@Controller
@RequestMapping("/system/detail")
public class OpenaiChatDetailController extends BaseController
{
    private String prefix = "openai/detail";

    @Autowired
    private IOpenaiChatDetailService openaiChatDetailService;
    @Autowired
    private IOpenaiPromptsService openaiPromptsService;

    @RequiresPermissions("system:detail:view")
    @GetMapping()
    public String detail(ModelMap mmap)
    {
        mmap.put("userId", "");
        return prefix + "/detail";
    }

    /**
     * 查询聊天记录详情列表
     */
    @RequiresPermissions("system:detail:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OpenaiChatDetail openaiChatDetail)
    {
        startPage();
        List<OpenaiChatDetail> list = openaiChatDetailService.selectOpenaiChatDetailList(openaiChatDetail);
        for (OpenaiChatDetail chatDetail : list) {
            OpenaiPrompts openaiPrompts = openaiPromptsService.selectOpenaiPromptsById(chatDetail.getMId());
            chatDetail.setMName(openaiPrompts.getTitle());
        }
        return getDataTable(list);
    }

    /**
     * 导出聊天记录详情列表
     */
    @RequiresPermissions("system:detail:export")
    @Log(title = "聊天记录详情", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OpenaiChatDetail openaiChatDetail)
    {
        List<OpenaiChatDetail> list = openaiChatDetailService.selectOpenaiChatDetailList(openaiChatDetail);
        ExcelUtil<OpenaiChatDetail> util = new ExcelUtil<OpenaiChatDetail>(OpenaiChatDetail.class);
        return util.exportExcel(list, "聊天记录详情数据");
    }

    /**
     * 新增聊天记录详情
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存聊天记录详情
     */
    @RequiresPermissions("system:detail:add")
    @Log(title = "聊天记录详情", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OpenaiChatDetail openaiChatDetail)
    {
        return toAjax(openaiChatDetailService.insertOpenaiChatDetail(openaiChatDetail));
    }

    /**
     * 修改聊天记录详情
     */
    @RequiresPermissions("system:detail:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        OpenaiChatDetail openaiChatDetail = openaiChatDetailService.selectOpenaiChatDetailById(id);
        mmap.put("openaiChatDetail", openaiChatDetail);
        return prefix + "/edit";
    }

    /**
     * 修改保存聊天记录详情
     */
    @RequiresPermissions("system:detail:edit")
    @Log(title = "聊天记录详情", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OpenaiChatDetail openaiChatDetail)
    {
        return toAjax(openaiChatDetailService.updateOpenaiChatDetail(openaiChatDetail));
    }

    /**
     * 删除聊天记录详情
     */
    @RequiresPermissions("system:detail:remove")
    @Log(title = "聊天记录详情", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(openaiChatDetailService.deleteOpenaiChatDetailByIds(ids));
    }

    @RequiresPermissions("system:detail:view")
    @GetMapping("/detailByUserId/{userId}")
    public String detailByUserId(@PathVariable("userId") Long userId, ModelMap mmap)
    {
        mmap.put("userId", userId);
        return prefix + "/detail";
    }

}
