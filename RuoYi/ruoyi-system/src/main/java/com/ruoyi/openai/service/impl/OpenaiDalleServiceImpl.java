package com.ruoyi.openai.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.openai.domain.OpenaiDalle;
import com.ruoyi.openai.mapper.OpenaiDalleMapper;
import com.ruoyi.openai.service.IOpenaiDalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.core.text.Convert;

/**
 * DALL.EService业务层处理
 * 
 * @author ruoyi
 * @date 2023-11-27
 */
@Service
public class OpenaiDalleServiceImpl implements IOpenaiDalleService
{
    @Autowired
    private OpenaiDalleMapper openaiDalleMapper;

    /**
     * 查询DALL.E
     * 
     * @param id DALL.E主键
     * @return DALL.E
     */
    @Override
    public OpenaiDalle selectOpenaiDalleById(Long id)
    {
        return openaiDalleMapper.selectOpenaiDalleById(id);
    }

    /**
     * 查询DALL.E列表
     * 
     * @param openaiDalle DALL.E
     * @return DALL.E
     */
    @Override
    public List<OpenaiDalle> selectOpenaiDalleList(OpenaiDalle openaiDalle)
    {
        return openaiDalleMapper.selectOpenaiDalleList(openaiDalle);
    }

    /**
     * 新增DALL.E
     * 
     * @param openaiDalle DALL.E
     * @return 结果
     */
    @Override
    public int insertOpenaiDalle(OpenaiDalle openaiDalle)
    {
        openaiDalle.setCreateTime(DateUtils.getNowDate());
        return openaiDalleMapper.insertOpenaiDalle(openaiDalle);
    }

    /**
     * 修改DALL.E
     * 
     * @param openaiDalle DALL.E
     * @return 结果
     */
    @Override
    public int updateOpenaiDalle(OpenaiDalle openaiDalle)
    {
        openaiDalle.setUpdateTime(DateUtils.getNowDate());
        return openaiDalleMapper.updateOpenaiDalle(openaiDalle);
    }

    /**
     * 批量删除DALL.E
     * 
     * @param ids 需要删除的DALL.E主键
     * @return 结果
     */
    @Override
    public int deleteOpenaiDalleByIds(String ids)
    {
        return openaiDalleMapper.deleteOpenaiDalleByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除DALL.E信息
     * 
     * @param id DALL.E主键
     * @return 结果
     */
    @Override
    public int deleteOpenaiDalleById(Long id)
    {
        return openaiDalleMapper.deleteOpenaiDalleById(id);
    }
}
