package com.ruoyi.openai.service;

import com.ruoyi.openai.domain.OpenaiPromptsType;

import java.util.List;

/**
 * 类型Service接口
 * 
 * @author ruoyi
 * @date 2023-07-06
 */
public interface IOpenaiPromptsTypeService 
{
    /**
     * 查询类型
     * 
     * @param id 类型主键
     * @return 类型
     */
    public OpenaiPromptsType selectOpenaiPromptsTypeById(Long id);

    /**
     * 查询类型列表
     * 
     * @param openaiPromptsType 类型
     * @return 类型集合
     */
    public List<OpenaiPromptsType> selectOpenaiPromptsTypeList(OpenaiPromptsType openaiPromptsType);

    /**
     * 新增类型
     * 
     * @param openaiPromptsType 类型
     * @return 结果
     */
    public int insertOpenaiPromptsType(OpenaiPromptsType openaiPromptsType);

    /**
     * 修改类型
     * 
     * @param openaiPromptsType 类型
     * @return 结果
     */
    public int updateOpenaiPromptsType(OpenaiPromptsType openaiPromptsType);

    /**
     * 批量删除类型
     * 
     * @param ids 需要删除的类型主键集合
     * @return 结果
     */
    public int deleteOpenaiPromptsTypeByIds(String ids);

    /**
     * 删除类型信息
     * 
     * @param id 类型主键
     * @return 结果
     */
    public int deleteOpenaiPromptsTypeById(Long id);
}
