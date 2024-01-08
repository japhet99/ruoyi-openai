package com.ruoyi.openai.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.openai.domain.OpenaiDeptCode;
import com.ruoyi.openai.domain.OpenaiUserInfo;
import com.ruoyi.openai.mapper.OpenaiUserInfoMapper;
import com.ruoyi.openai.service.IOpenaiDeptCodeService;
import com.ruoyi.openai.service.IOpenaiUserInfoService;
import com.ruoyi.system.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户信息详情Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-05-23
 */
@Service
public class OpenaiUserInfoServiceImpl implements IOpenaiUserInfoService
{
    @Autowired
    private OpenaiUserInfoMapper openaiUserInfoMapper;
    @Autowired
    private IOpenaiDeptCodeService deptCodeService;
    @Autowired
    private ISysConfigService configService;

    /**
     * 查询用户信息详情
     * 
     * @param id 用户信息详情主键
     * @return 用户信息详情
     */
    @Override
    public OpenaiUserInfo selectOpenaiUserInfoById(Long id)
    {
        return openaiUserInfoMapper.selectOpenaiUserInfoById(id);
    }

    /**
     * 查询用户信息详情列表
     * 
     * @param openaiUserInfo 用户信息详情
     * @return 用户信息详情
     */
    @Override
    public List<OpenaiUserInfo> selectOpenaiUserInfoList(OpenaiUserInfo openaiUserInfo)
    {
        return openaiUserInfoMapper.selectOpenaiUserInfoList(openaiUserInfo);
    }

    /**
     * 新增用户信息详情
     * 
     * @param openaiUserInfo 用户信息详情
     * @return 结果
     */
    @Override
    public int insertOpenaiUserInfo(OpenaiUserInfo openaiUserInfo)
    {
        openaiUserInfo.setCreateTime(DateUtils.getNowDate());
        return openaiUserInfoMapper.insertOpenaiUserInfo(openaiUserInfo);
    }

    /**
     * 修改用户信息详情
     * 
     * @param openaiUserInfo 用户信息详情
     * @return 结果
     */
    @Override
    public int updateOpenaiUserInfo(OpenaiUserInfo openaiUserInfo)
    {
        openaiUserInfo.setUpdateTime(DateUtils.getNowDate());
        return openaiUserInfoMapper.updateOpenaiUserInfo(openaiUserInfo);
    }

    /**
     * 批量删除用户信息详情
     * 
     * @param ids 需要删除的用户信息详情主键
     * @return 结果
     */
    @Override
    public int deleteOpenaiUserInfoByIds(String ids)
    {
        return openaiUserInfoMapper.deleteOpenaiUserInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除用户信息详情信息
     * 
     * @param id 用户信息详情主键
     * @return 结果
     */
    @Override
    public int deleteOpenaiUserInfoById(Long id)
    {
        return openaiUserInfoMapper.deleteOpenaiUserInfoById(id);
    }

    /**
     * 修改3.5的金额次数
     * @param userId
     * @param moneyTime
     * @return
     */
    @Override
    public int updateOpenaiUserInfoByMoneyTime(Long userId,Integer moneyTime) {
        return openaiUserInfoMapper.updateOpenaiUserInfoByMoneyTime(moneyTime,userId);
    }

    /**
     * 修改3.5的免费次数
     * @param userId
     * @param freeTime
     * @return
     */
    @Override
    public int updateOpenaiUserInfoByFreeTime(Long userId,Integer freeTime) {
        return openaiUserInfoMapper.updateOpenaiUserInfoByFreeTime(freeTime,userId);
    }


    /**
     * 修改 4.0 的金额次数
     * @param userId
     * @param moneyTime
     * @return
     */
    @Override
    public int updateOpenaiUserInfoByMoneyTimeFour(Long userId,Integer moneyTime) {
        return openaiUserInfoMapper.updateOpenaiUserInfoByMoneyTimeFour(moneyTime,userId);
    }

    /**
     *
     * @param userId
     * @return
     */
    @Override
    public OpenaiUserInfo selectOpenaiUserInfoByUserId(Long userId) {
        return openaiUserInfoMapper.selectOpenaiUserInfoByUserId(userId);
    }

    @Override
    public OpenaiUserInfo selectUserByIp(String ip) {
        return openaiUserInfoMapper.selectUserByIp(ip);
    }


    /**
     * 修改 佣金金额
     * @param userId
     * @param inviteMoney
     * @return
     */
    @Override
    public int updateOpenaiUserInfoByMoney(Long userId, BigDecimal inviteMoney) {
        return openaiUserInfoMapper.updateOpenaiUserInfoByMoney(userId,inviteMoney);
    }

    @Override
    public AjaxResult getMyPromoteInfo(Long userId) {
        Map<String,Object> map = new HashMap<>();
        OpenaiUserInfo openaiUserInfo = openaiUserInfoMapper.selectOpenaiUserInfoByUserId(userId);
        OpenaiDeptCode openaiDeptCode = deptCodeService.selectOpenaiDeptCodeByUserId(userId);
        BigDecimal registerInvitePayMoney = new BigDecimal(configService.selectConfigByKeyAndCompanyId(openaiUserInfo.getCompanyId(),"register_invite_pay_money"));
        Integer inviteMoneyTime = Integer.parseInt(configService.selectConfigByKeyAndCompanyId(openaiUserInfo.getCompanyId(),"register_invite_moneyTime"));
        String localUrl = configService.selectConfigByKeyAndCompanyId(openaiUserInfo.getCompanyId(),"local_url");
        map.put("money",openaiUserInfo.getMoney());
        map.put("totalMoney",openaiUserInfo.getTotalMoney());
        map.put("freezeMoney",openaiUserInfo.getFreezeMoney());
        map.put("myPromoteCount",openaiUserInfo.getInviteCount());
        map.put("promoteCode",localUrl+"?code="+openaiDeptCode.getCode());
        map.put("registerInvitePayMoney",registerInvitePayMoney.multiply(new BigDecimal(100)).intValue()+"%");
        map.put("buyCount",openaiUserInfo.getBuyCount());
        map.put("inviteMoneyTime",inviteMoneyTime+"次");
        return AjaxResult.success(map);
    }

    /**
     * 提现
     * @param userId
     * @return
     */
    @Override
    public AjaxResult getMoney(Long userId) {
        BigDecimal withdrawalMoney = new BigDecimal(configService.selectConfigByKey("withdrawal_money"));
        OpenaiUserInfo openaiUserInfo = openaiUserInfoMapper.selectOpenaiUserInfoByUserId(userId);
        if(openaiUserInfo.getMoney().compareTo(withdrawalMoney) < 0){
            return AjaxResult.success("佣金需要大于"+withdrawalMoney+"才能提现! 继续努力呀!!! 冲冲冲");
        }
        return AjaxResult.success("请加群联系管理员进行提现!");
    }

    @Override
    public int updateOpenaiUserInfoByBuyCount(Long userId, BigDecimal inviteMoney) {
        return openaiUserInfoMapper.updateOpenaiUserInfoByBuyCount(userId,inviteMoney);
    }

    @Override
    public List<Map<String,Object>> selectOpenaiUserInfoListByPartner(Long companyId,String begin,String end) {
        return openaiUserInfoMapper.selectOpenaiUserInfoListByPartner(companyId,begin,end);
    }

    @Override
    public List<Map<String,Object>> selectOpenaiOrderInfoListByPartner(Long companyId,String begin,String end) {
        return openaiUserInfoMapper.selectOpenaiOrderInfoListByPartner(companyId,begin,end);
    }

    @Override
    public List<Map<String,Object>> selectOpenaiActiveInfoListByPartner(Long companyId,String begin,String end) {
        return openaiUserInfoMapper.selectOpenaiActiveInfoListByPartner(companyId,begin,end);
    }

    @Override
    public Integer selectOpenaiUserInfoCountByPartner(Long companyId) {
        return openaiUserInfoMapper.selectOpenaiUserInfoCountByPartner(companyId);
    }

    @Override
    public Integer selectOpenaiOrderCountByPartner(Long companyId) {
        return openaiUserInfoMapper.selectOpenaiOrderCountByPartner(companyId);
    }

    @Override
    public Integer selectOpenaiOrderSuccessCountByPartner(Long companyId) {
        return openaiUserInfoMapper.selectOpenaiOrderSuccessCountByPartner(companyId);
    }

    @Override
    public Integer updateOpenaiUserInfoByDallETime(Long userId,Integer dallETime) {
        return openaiUserInfoMapper.updateOpenaiUserInfoByDallETime(dallETime,userId);
    }

    @Override
    public Integer updateOpenaiUserInfoByMJMoneyTime(Long userId, Integer mjMoneyTime) {
        return openaiUserInfoMapper.updateOpenaiUserInfoByMJMoneyTime(mjMoneyTime,userId);
    }
}
