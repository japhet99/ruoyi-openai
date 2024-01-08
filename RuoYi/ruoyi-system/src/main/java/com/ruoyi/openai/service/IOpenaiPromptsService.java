package com.ruoyi.openai.service;

import com.ruoyi.openai.domain.OpenaiPrompts;

import java.util.List;

/**
 * promptsService接口
 * 
 * @author ruoyi
 * @date 2023-06-30
 */
public interface IOpenaiPromptsService 
{
    /**
     * 查询prompts
     * 
     * @param id prompts主键
     * @return prompts
     */
    public OpenaiPrompts selectOpenaiPromptsById(Long id);

    /**
     * 查询prompts列表
     * 
     * @param openaiPrompts prompts
     * @return prompts集合
     */
    public List<OpenaiPrompts> selectOpenaiPromptsList(OpenaiPrompts openaiPrompts);

    /**
     * 新增prompts
     * 
     * @param openaiPrompts prompts
     * @return 结果
     */
    public int insertOpenaiPrompts(OpenaiPrompts openaiPrompts);

    /**
     * 修改prompts
     * 
     * @param openaiPrompts prompts
     * @return 结果
     */
    public int updateOpenaiPrompts(OpenaiPrompts openaiPrompts);

    /**
     * 批量删除prompts
     * 
     * @param ids 需要删除的prompts主键集合
     * @return 结果
     */
    public int deleteOpenaiPromptsByIds(String ids);

    /**
     * 删除prompts信息
     * 
     * @param id prompts主键
     * @return 结果
     */
    public int deleteOpenaiPromptsById(Long id);
}
