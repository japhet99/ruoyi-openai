package com.ruoyi.openai.service.impl;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.PayUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.openai.domain.OpenaiChatOrder;
import com.ruoyi.openai.domain.OpenaiChatPlan;
import com.ruoyi.openai.domain.OpenaiUserInfo;
import com.ruoyi.openai.mapper.OpenaiChatOrderMapper;
import com.ruoyi.openai.service.IOpenaiChatOrderService;
import com.ruoyi.openai.service.IOpenaiChatPlanService;
import com.ruoyi.openai.service.IOpenaiUserInfoService;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.ISysDictDataService;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户订单Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-07-04
 */
@Service
public class OpenaiChatOrderServiceImpl implements IOpenaiChatOrderService
{

    private static final Logger logger = LoggerFactory.getLogger(OpenaiChatOrderServiceImpl.class);

    @Autowired
    private OpenaiChatOrderMapper openaiChatOrderMapper;

    /**
     * 查询用户订单
     * 
     * @param id 用户订单主键
     * @return 用户订单
     */
    @Override
    public OpenaiChatOrder selectOpenaiChatOrderById(Long id)
    {
        return openaiChatOrderMapper.selectOpenaiChatOrderById(id);
    }

    /**
     * 查询用户订单列表
     * 
     * @param openaiChatOrder 用户订单
     * @return 用户订单
     */
    @Override
    public List<OpenaiChatOrder> selectOpenaiChatOrderList(OpenaiChatOrder openaiChatOrder)
    {
        return openaiChatOrderMapper.selectOpenaiChatOrderList(openaiChatOrder);
    }

    /**
     * 新增用户订单
     * 
     * @param openaiChatOrder 用户订单
     * @return 结果
     */
    @Override
    public int insertOpenaiChatOrder(OpenaiChatOrder openaiChatOrder)
    {
        openaiChatOrder.setCreateTime(DateUtils.getNowDate());
        return openaiChatOrderMapper.insertOpenaiChatOrder(openaiChatOrder);
    }

    /**
     * 修改用户订单
     * 
     * @param openaiChatOrder 用户订单
     * @return 结果
     */
    @Override
    public int updateOpenaiChatOrder(OpenaiChatOrder openaiChatOrder)
    {
        openaiChatOrder.setUpdateTime(DateUtils.getNowDate());
        return openaiChatOrderMapper.updateOpenaiChatOrder(openaiChatOrder);
    }

    /**
     * 批量删除用户订单
     * 
     * @param ids 需要删除的用户订单主键
     * @return 结果
     */
    @Override
    public int deleteOpenaiChatOrderByIds(String ids)
    {
        return openaiChatOrderMapper.deleteOpenaiChatOrderByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除用户订单信息
     * 
     * @param id 用户订单主键
     * @return 结果
     */
    @Override
    public int deleteOpenaiChatOrderById(Long id)
    {
        return openaiChatOrderMapper.deleteOpenaiChatOrderById(id);
    }


    /**
     * 根据userId查询成功订单列表
     * @param userId 用户id
     * @return
     */
    @Override
    public List<OpenaiChatOrder> selectOpenaiChatOrderListByUserId(Long userId) {
        return openaiChatOrderMapper.selectOpenaiChatOrderListByUserId(userId);
    }


    private String param = "chatgpt";

    /**
     * 创建订单
     * @param userId
     * @Param pId
     *
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult createOrder(Long userId,Long pId) {
        //todo 自己对接
        return AjaxResult.success("xxxxxxxx");
    }

    /**
     * 关单
     * @param vmqUrl
     * @param param2
     * @param chatOrder
     */
    public void closeOrder(String vmqUrl,String param2,OpenaiChatOrder chatOrder){
        logger.info("关闭订单响应结果===");
    }


}
