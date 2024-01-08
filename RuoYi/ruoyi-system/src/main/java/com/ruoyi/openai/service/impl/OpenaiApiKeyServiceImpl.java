package com.ruoyi.openai.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.openai.domain.OpenaiApiKey;
import com.ruoyi.openai.mapper.OpenaiApiKeyMapper;
import com.ruoyi.openai.service.IOpenaiApiKeyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * api_key 列Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-07-10
 */
@Service
public class OpenaiApiKeyServiceImpl implements IOpenaiApiKeyService
{
    @Autowired
    private OpenaiApiKeyMapper openaiApiKeyMapper;

    /**
     * 查询api_key 列
     * 
     * @param id api_key 列主键
     * @return api_key 列
     */
    @Override
    public OpenaiApiKey selectOpenaiApiKeyById(Long id)
    {
        return openaiApiKeyMapper.selectOpenaiApiKeyById(id);
    }

    /**
     * 查询api_key 列列表
     * 
     * @param openaiApiKey api_key 列
     * @return api_key 列
     */
    @Override
    public List<OpenaiApiKey> selectOpenaiApiKeyList(OpenaiApiKey openaiApiKey)
    {
        return openaiApiKeyMapper.selectOpenaiApiKeyList(openaiApiKey);
    }

    /**
     * 新增api_key 列
     * 
     * @param openaiApiKey api_key 列
     * @return 结果
     */
    @Override
    public int insertOpenaiApiKey(OpenaiApiKey openaiApiKey)
    {
        openaiApiKey.setCreateTime(DateUtils.getNowDate());
        return openaiApiKeyMapper.insertOpenaiApiKey(openaiApiKey);
    }

    /**
     * 修改api_key 列
     * 
     * @param openaiApiKey api_key 列
     * @return 结果
     */
    @Override
    public int updateOpenaiApiKey(OpenaiApiKey openaiApiKey)
    {
        openaiApiKey.setUpdateTime(DateUtils.getNowDate());
        return openaiApiKeyMapper.updateOpenaiApiKey(openaiApiKey);
    }

    /**
     * 批量删除api_key 列
     * 
     * @param ids 需要删除的api_key 列主键
     * @return 结果
     */
    @Override
    public int deleteOpenaiApiKeyByIds(String ids)
    {
        return openaiApiKeyMapper.deleteOpenaiApiKeyByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除api_key 列信息
     * 
     * @param id api_key 列主键
     * @return 结果
     */
    @Override
    public int deleteOpenaiApiKeyById(Long id)
    {
        return openaiApiKeyMapper.deleteOpenaiApiKeyById(id);
    }

    //当前轮询数值
    private int currentIndex = 0;
    private static final Logger logger = LoggerFactory.getLogger(OpenaiApiKeyServiceImpl.class);

    /**
     * 使用轮询算法获取一个apiKey
     * @return
     */
    @Override
    public synchronized String roundRobinGetByType() {
        String apiKey = "";
        OpenaiApiKey openaiApiKey = new OpenaiApiKey();
        openaiApiKey.setStatus(0);
        List<OpenaiApiKey> openaiApiKeys = openaiApiKeyMapper.selectOpenaiApiKeyList(openaiApiKey);
        if(openaiApiKeys.size()==1){
            apiKey = openaiApiKeys.get(0).getApiKey();
        }else{
            OpenaiApiKey openaiApiKey1 = openaiApiKeys.get(currentIndex);
            if(openaiApiKey1 !=null ){
                apiKey= openaiApiKey1.getApiKey();
            }
            currentIndex = (currentIndex + 1) % openaiApiKeys.size(); // 更新索引，确保在列表范围内循环
            logger.info("正在轮询获取apikey, 当前下标为:{}, apikey为:{}", currentIndex, apiKey);
        }
        return apiKey;
    }


}
