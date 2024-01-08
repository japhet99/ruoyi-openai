package com.ruoyi.openai.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.openai.domain.OpenaiPrompts;
import com.ruoyi.openai.mapper.OpenaiPromptsMapper;
import com.ruoyi.openai.service.IOpenaiPromptsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * promptsService业务层处理
 * 
 * @author ruoyi
 * @date 2023-06-30
 */
@Service
public class OpenaiPromptsServiceImpl implements IOpenaiPromptsService
{
    @Autowired
    private OpenaiPromptsMapper openaiPromptsMapper;

    /**
     * 查询prompts
     * 
     * @param id prompts主键
     * @return prompts
     */
    @Override
    public OpenaiPrompts selectOpenaiPromptsById(Long id)
    {
        return openaiPromptsMapper.selectOpenaiPromptsById(id);
    }

    /**
     * 查询prompts列表
     * 
     * @param openaiPrompts prompts
     * @return prompts
     */
    @Override
    public List<OpenaiPrompts> selectOpenaiPromptsList(OpenaiPrompts openaiPrompts)
    {
        return openaiPromptsMapper.selectOpenaiPromptsList(openaiPrompts);
    }

    /**
     * 新增prompts
     * 
     * @param openaiPrompts prompts
     * @return 结果
     */
    @Override
    public int insertOpenaiPrompts(OpenaiPrompts openaiPrompts)
    {
        openaiPrompts.setCreateTime(DateUtils.getNowDate());
        return openaiPromptsMapper.insertOpenaiPrompts(openaiPrompts);
    }

    /**
     * 修改prompts
     * 
     * @param openaiPrompts prompts
     * @return 结果
     */
    @Override
    public int updateOpenaiPrompts(OpenaiPrompts openaiPrompts)
    {
        openaiPrompts.setUpdateTime(DateUtils.getNowDate());
        return openaiPromptsMapper.updateOpenaiPrompts(openaiPrompts);
    }

    /**
     * 批量删除prompts
     * 
     * @param ids 需要删除的prompts主键
     * @return 结果
     */
    @Override
    public int deleteOpenaiPromptsByIds(String ids)
    {
        return openaiPromptsMapper.deleteOpenaiPromptsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除prompts信息
     * 
     * @param id prompts主键
     * @return 结果
     */
    @Override
    public int deleteOpenaiPromptsById(Long id)
    {
        return openaiPromptsMapper.deleteOpenaiPromptsById(id);
    }
}
