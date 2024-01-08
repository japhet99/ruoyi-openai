package com.ruoyi.openai.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.openai.domain.OpenaiFaq;
import com.ruoyi.openai.mapper.OpenaiFaqMapper;
import com.ruoyi.openai.service.IOpenaiFaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.core.text.Convert;

/**
 * FAQ 常用问题解答Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-05-23
 */
@Service
public class OpenaiFaqServiceImpl implements IOpenaiFaqService
{
    @Autowired
    private OpenaiFaqMapper openaiFaqMapper;

    /**
     * 查询FAQ 常用问题解答
     * 
     * @param id FAQ 常用问题解答主键
     * @return FAQ 常用问题解答
     */
    @Override
    public OpenaiFaq selectOpenaiFaqById(Long id)
    {
        return openaiFaqMapper.selectOpenaiFaqById(id);
    }

    /**
     * 查询FAQ 常用问题解答列表
     * 
     * @param openaiFaq FAQ 常用问题解答
     * @return FAQ 常用问题解答
     */
    @Override
    public List<OpenaiFaq> selectOpenaiFaqList(OpenaiFaq openaiFaq)
    {
        return openaiFaqMapper.selectOpenaiFaqList(openaiFaq);
    }

    /**
     * 新增FAQ 常用问题解答
     * 
     * @param openaiFaq FAQ 常用问题解答
     * @return 结果
     */
    @Override
    public int insertOpenaiFaq(OpenaiFaq openaiFaq)
    {
        openaiFaq.setCreateTime(DateUtils.getNowDate());
        return openaiFaqMapper.insertOpenaiFaq(openaiFaq);
    }

    /**
     * 修改FAQ 常用问题解答
     * 
     * @param openaiFaq FAQ 常用问题解答
     * @return 结果
     */
    @Override
    public int updateOpenaiFaq(OpenaiFaq openaiFaq)
    {
        openaiFaq.setUpdateTime(DateUtils.getNowDate());
        return openaiFaqMapper.updateOpenaiFaq(openaiFaq);
    }

    /**
     * 批量删除FAQ 常用问题解答
     * 
     * @param ids 需要删除的FAQ 常用问题解答主键
     * @return 结果
     */
    @Override
    public int deleteOpenaiFaqByIds(String ids)
    {
        return openaiFaqMapper.deleteOpenaiFaqByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除FAQ 常用问题解答信息
     * 
     * @param id FAQ 常用问题解答主键
     * @return 结果
     */
    @Override
    public int deleteOpenaiFaqById(Long id)
    {
        return openaiFaqMapper.deleteOpenaiFaqById(id);
    }
}
