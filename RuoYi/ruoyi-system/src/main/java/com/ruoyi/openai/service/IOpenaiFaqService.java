package com.ruoyi.openai.service;

import com.ruoyi.openai.domain.OpenaiFaq;

import java.util.List;

/**
 * FAQ 常用问题解答Service接口
 * 
 * @author ruoyi
 * @date 2023-05-23
 */
public interface IOpenaiFaqService 
{
    /**
     * 查询FAQ 常用问题解答
     * 
     * @param id FAQ 常用问题解答主键
     * @return FAQ 常用问题解答
     */
    public OpenaiFaq selectOpenaiFaqById(Long id);

    /**
     * 查询FAQ 常用问题解答列表
     * 
     * @param openaiFaq FAQ 常用问题解答
     * @return FAQ 常用问题解答集合
     */
    public List<OpenaiFaq> selectOpenaiFaqList(OpenaiFaq openaiFaq);

    /**
     * 新增FAQ 常用问题解答
     * 
     * @param openaiFaq FAQ 常用问题解答
     * @return 结果
     */
    public int insertOpenaiFaq(OpenaiFaq openaiFaq);

    /**
     * 修改FAQ 常用问题解答
     * 
     * @param openaiFaq FAQ 常用问题解答
     * @return 结果
     */
    public int updateOpenaiFaq(OpenaiFaq openaiFaq);

    /**
     * 批量删除FAQ 常用问题解答
     * 
     * @param ids 需要删除的FAQ 常用问题解答主键集合
     * @return 结果
     */
    public int deleteOpenaiFaqByIds(String ids);

    /**
     * 删除FAQ 常用问题解答信息
     * 
     * @param id FAQ 常用问题解答主键
     * @return 结果
     */
    public int deleteOpenaiFaqById(Long id);
}
