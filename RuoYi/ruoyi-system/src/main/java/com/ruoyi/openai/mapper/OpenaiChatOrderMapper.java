package com.ruoyi.openai.mapper;

import com.ruoyi.openai.domain.OpenaiChatOrder;

import java.util.List;

/**
 * 用户订单Mapper接口
 * 
 * @author ruoyi
 * @date 2023-07-04
 */
public interface OpenaiChatOrderMapper 
{
    /**
     * 查询用户订单
     * 
     * @param id 用户订单主键
     * @return 用户订单
     */
    public OpenaiChatOrder selectOpenaiChatOrderById(Long id);

    /**
     * 查询用户订单列表
     * 
     * @param openaiChatOrder 用户订单
     * @return 用户订单集合
     */
    public List<OpenaiChatOrder> selectOpenaiChatOrderList(OpenaiChatOrder openaiChatOrder);

    /**
     * 新增用户订单
     * 
     * @param openaiChatOrder 用户订单
     * @return 结果
     */
    public int insertOpenaiChatOrder(OpenaiChatOrder openaiChatOrder);

    /**
     * 修改用户订单
     * 
     * @param openaiChatOrder 用户订单
     * @return 结果
     */
    public int updateOpenaiChatOrder(OpenaiChatOrder openaiChatOrder);

    /**
     * 删除用户订单
     * 
     * @param id 用户订单主键
     * @return 结果
     */
    public int deleteOpenaiChatOrderById(Long id);

    /**
     * 批量删除用户订单
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOpenaiChatOrderByIds(String[] ids);

    /**
     * 根据userId查询成功订单列表
     * @param userId
     * @return
     */
    List<OpenaiChatOrder> selectOpenaiChatOrderListByUserId(Long userId);
}
