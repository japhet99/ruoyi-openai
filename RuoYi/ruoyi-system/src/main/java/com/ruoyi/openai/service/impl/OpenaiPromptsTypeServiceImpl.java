package com.ruoyi.openai.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.openai.domain.OpenaiPromptsType;
import com.ruoyi.openai.mapper.OpenaiPromptsTypeMapper;
import com.ruoyi.openai.service.IOpenaiPromptsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类型Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-07-06
 */
@Service
public class OpenaiPromptsTypeServiceImpl implements IOpenaiPromptsTypeService
{
    @Autowired
    private OpenaiPromptsTypeMapper openaiPromptsTypeMapper;

    /**
     * 查询类型
     * 
     * @param id 类型主键
     * @return 类型
     */
    @Override
    public OpenaiPromptsType selectOpenaiPromptsTypeById(Long id)
    {
        return openaiPromptsTypeMapper.selectOpenaiPromptsTypeById(id);
    }

    /**
     * 查询类型列表
     * 
     * @param openaiPromptsType 类型
     * @return 类型
     */
    @Override
    public List<OpenaiPromptsType> selectOpenaiPromptsTypeList(OpenaiPromptsType openaiPromptsType)
    {
        return openaiPromptsTypeMapper.selectOpenaiPromptsTypeList(openaiPromptsType);
    }

    /**
     * 新增类型
     * 
     * @param openaiPromptsType 类型
     * @return 结果
     */
    @Override
    public int insertOpenaiPromptsType(OpenaiPromptsType openaiPromptsType)
    {
        openaiPromptsType.setCreateTime(DateUtils.getNowDate());
        return openaiPromptsTypeMapper.insertOpenaiPromptsType(openaiPromptsType);
    }

    /**
     * 修改类型
     * 
     * @param openaiPromptsType 类型
     * @return 结果
     */
    @Override
    public int updateOpenaiPromptsType(OpenaiPromptsType openaiPromptsType)
    {
        openaiPromptsType.setUpdateTime(DateUtils.getNowDate());
        return openaiPromptsTypeMapper.updateOpenaiPromptsType(openaiPromptsType);
    }

    /**
     * 批量删除类型
     * 
     * @param ids 需要删除的类型主键
     * @return 结果
     */
    @Override
    public int deleteOpenaiPromptsTypeByIds(String ids)
    {
        return openaiPromptsTypeMapper.deleteOpenaiPromptsTypeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除类型信息
     * 
     * @param id 类型主键
     * @return 结果
     */
    @Override
    public int deleteOpenaiPromptsTypeById(Long id)
    {
        return openaiPromptsTypeMapper.deleteOpenaiPromptsTypeById(id);
    }
}
