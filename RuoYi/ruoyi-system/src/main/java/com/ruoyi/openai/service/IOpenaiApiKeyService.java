package com.ruoyi.openai.service;

import com.ruoyi.openai.domain.OpenaiApiKey;

import java.util.List;

/**
 * api_key 列Service接口
 * 
 * @author ruoyi
 * @date 2023-07-10
 */
public interface IOpenaiApiKeyService 
{
    /**
     * 查询api_key 列
     * 
     * @param id api_key 列主键
     * @return api_key 列
     */
    public OpenaiApiKey selectOpenaiApiKeyById(Long id);

    /**
     * 查询api_key 列列表
     * 
     * @param openaiApiKey api_key 列
     * @return api_key 列集合
     */
    public List<OpenaiApiKey> selectOpenaiApiKeyList(OpenaiApiKey openaiApiKey);

    /**
     * 新增api_key 列
     * 
     * @param openaiApiKey api_key 列
     * @return 结果
     */
    public int insertOpenaiApiKey(OpenaiApiKey openaiApiKey);

    /**
     * 修改api_key 列
     * 
     * @param openaiApiKey api_key 列
     * @return 结果
     */
    public int updateOpenaiApiKey(OpenaiApiKey openaiApiKey);

    /**
     * 批量删除api_key 列
     * 
     * @param ids 需要删除的api_key 列主键集合
     * @return 结果
     */
    public int deleteOpenaiApiKeyByIds(String ids);

    /**
     * 删除api_key 列信息
     * 
     * @param id api_key 列主键
     * @return 结果
     */
    public int deleteOpenaiApiKeyById(Long id);

    /**
     *使用轮询算法获取一个apiKey
     * @return
     */
    String roundRobinGetByType();

}
