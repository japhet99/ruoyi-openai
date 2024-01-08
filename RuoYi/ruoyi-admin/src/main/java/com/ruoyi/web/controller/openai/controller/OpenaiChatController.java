package com.ruoyi.web.controller.openai.controller;

import java.util.List;

import com.ruoyi.openai.domain.OpenaiChat;
import com.ruoyi.openai.service.IOpenaiChatService;
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
 * 聊天记录Controller
 * 
 * @author ruoyi
 * @date 2023-05-23
 */
@Controller
@RequestMapping("/system/chat")
public class OpenaiChatController extends BaseController
{
    private String prefix = "openai/chat";

    @Autowired
    private IOpenaiChatService openaiChatService;

    @RequiresPermissions("system:chat:view")
    @GetMapping()
    public String chat()
    {
        return prefix + "/chat";
    }

    /**
     * 查询聊天记录列表
     */
    @RequiresPermissions("system:chat:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OpenaiChat openaiChat)
    {
        startPage();
        List<OpenaiChat> list = openaiChatService.selectOpenaiChatList(openaiChat);
        return getDataTable(list);
    }

    /**
     * 导出聊天记录列表
     */
    @RequiresPermissions("system:chat:export")
    @Log(title = "聊天记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OpenaiChat openaiChat)
    {
        List<OpenaiChat> list = openaiChatService.selectOpenaiChatList(openaiChat);
        ExcelUtil<OpenaiChat> util = new ExcelUtil<OpenaiChat>(OpenaiChat.class);
        return util.exportExcel(list, "聊天记录数据");
    }

    /**
     * 新增聊天记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存聊天记录
     */
    @RequiresPermissions("system:chat:add")
    @Log(title = "聊天记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OpenaiChat openaiChat)
    {
        return toAjax(openaiChatService.insertOpenaiChat(openaiChat));
    }

    /**
     * 修改聊天记录
     */
    @RequiresPermissions("system:chat:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        OpenaiChat openaiChat = openaiChatService.selectOpenaiChatById(id);
        mmap.put("openaiChat", openaiChat);
        return prefix + "/edit";
    }

    /**
     * 修改保存聊天记录
     */
    @RequiresPermissions("system:chat:edit")
    @Log(title = "聊天记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OpenaiChat openaiChat)
    {
        return toAjax(openaiChatService.updateOpenaiChat(openaiChat));
    }

    /**
     * 删除聊天记录
     */
    @RequiresPermissions("system:chat:remove")
    @Log(title = "聊天记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(openaiChatService.deleteOpenaiChatByIds(ids));
    }
}
