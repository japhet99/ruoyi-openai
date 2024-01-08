package com.ruoyi.openai.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.openai.domain.OpenaiUserInfo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 用户信息详情Service接口
 * 
 * @author ruoyi
 * @date 2023-05-23
 */
public interface IOpenaiUserInfoService 
{
    /**
     * 查询用户信息详情
     * 
     * @param id 用户信息详情主键
     * @return 用户信息详情
     */
    public OpenaiUserInfo selectOpenaiUserInfoById(Long id);

    /**
     * 查询用户信息详情列表
     * 
     * @param openaiUserInfo 用户信息详情
     * @return 用户信息详情集合
     */
    public List<OpenaiUserInfo> selectOpenaiUserInfoList(OpenaiUserInfo openaiUserInfo);

    /**
     * 新增用户信息详情
     * 
     * @param openaiUserInfo 用户信息详情
     * @return 结果
     */
    public int insertOpenaiUserInfo(OpenaiUserInfo openaiUserInfo);

    /**
     * 修改用户信息详情
     * 
     * @param openaiUserInfo 用户信息详情
     * @return 结果
     */
    public int updateOpenaiUserInfo(OpenaiUserInfo openaiUserInfo);

    /**
     * 批量删除用户信息详情
     * 
     * @param ids 需要删除的用户信息详情主键集合
     * @return 结果
     */
    public int deleteOpenaiUserInfoByIds(String ids);

    /**
     * 删除用户信息详情信息
     * 
     * @param id 用户信息详情主键
     * @return 结果
     */
    public int deleteOpenaiUserInfoById(Long id);

    int updateOpenaiUserInfoByMoneyTime(Long userId,Integer moneyTime);

    int updateOpenaiUserInfoByFreeTime(Long userId,Integer freeTime);

    int updateOpenaiUserInfoByMoneyTimeFour(Long userId,Integer moneyTime);

    OpenaiUserInfo selectOpenaiUserInfoByUserId(Long userId);

    OpenaiUserInfo selectUserByIp(String ip);

    int updateOpenaiUserInfoByMoney(Long userId, BigDecimal inviteMoney);

    AjaxResult getMyPromoteInfo(Long userId);

    AjaxResult getMoney(Long userId);

    int updateOpenaiUserInfoByBuyCount(Long userId2, BigDecimal bigDecimal);

    List<Map<String,Object>> selectOpenaiUserInfoListByPartner(Long companyId,String begin,String end);

    List<Map<String, Object>> selectOpenaiOrderInfoListByPartner(Long companyId, String begin, String end);

    List<Map<String, Object>> selectOpenaiActiveInfoListByPartner(Long companyId, String begin, String end);

    Integer selectOpenaiUserInfoCountByPartner(Long companyId);

    Integer selectOpenaiOrderCountByPartner(Long companyId);

    Integer selectOpenaiOrderSuccessCountByPartner(Long companyId);

    Integer updateOpenaiUserInfoByDallETime(Long userId,Integer dallETime);

    Integer updateOpenaiUserInfoByMJMoneyTime(Long userId,Integer mjMoneyTime);
}
