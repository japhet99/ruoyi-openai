package com.ruoyi.framework.shiro.service;

import cn.hutool.core.math.MathUtil;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.*;
import com.ruoyi.framework.manager.AsyncManager;
import com.ruoyi.framework.manager.factory.AsyncFactory;
import com.ruoyi.openai.domain.OpenaiDeptCode;
import com.ruoyi.openai.domain.OpenaiUserAccountLog;
import com.ruoyi.openai.domain.OpenaiUserInfo;
import com.ruoyi.openai.service.IOpenaiDeptCodeService;
import com.ruoyi.openai.service.IOpenaiUserAccountLogService;
import com.ruoyi.openai.service.IOpenaiUserInfoService;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 注册校验方法
 * 
 * @author ruoyi
 */
@Component
public class SysRegisterService
{
    @Autowired
    private ISysUserService userService;

    @Autowired
    private SysPasswordService passwordService;

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private IOpenaiUserInfoService openaiUserInfoService;

    @Autowired
    private IOpenaiDeptCodeService openaiDeptCodeService;

    @Autowired
    private IOpenaiUserAccountLogService openaiUserAccountLogService;

    @Autowired
    private ISysDictDataService sysDictDataService;

    /**
     * 注册
     */
    @Transactional(rollbackFor = Exception.class)
    public String register(SysUser user) {
        String msg = "", loginName = user.getLoginName(), password = user.getPassword();
        String ip = ShiroUtils.getIp();
        System.out.println("ip===="+ip);
        OpenaiUserInfo openaiUserInfo2 = null;
        Long companyId = 1L;
        //判断域名是否有效
        SysDictData dictData = new SysDictData();
        dictData.setDictType("partner");
        dictData.setStatus("0");
        List<SysDictData> sysDictData = sysDictDataService.selectDictDataList(dictData);
        boolean prefixStatus = false;
        for (SysDictData sysDictDatum : sysDictData) {
            if(user.getPrefix().equals(sysDictDatum.getDictLabel())){
                prefixStatus = true;
                companyId = Long.parseLong(sysDictDatum.getDictValue());
                break;
            }
        }
        if(!prefixStatus){
            msg = "域名错误!";
            return msg;
        }

        //注册限制ip开关
        String registerIPStatus = configService.selectConfigByKeyAndCompanyId(companyId,"register_ip_status");
        if("0".equals(registerIPStatus)){
            if(StringUtils.isNotEmpty(ip)){
                openaiUserInfo2 = openaiUserInfoService.selectUserByIp(ip);
            }
        }

//        if (ShiroConstants.CAPTCHA_ERROR.equals(ServletUtils.getRequest().getAttribute(ShiroConstants.CURRENT_CAPTCHA)))
//        {
//            msg = "验证码错误";
//        }
        if(openaiUserInfo2!=null){
            msg = "注册失败!请联系管理员或者切换网络重试!!!";
        }else if (StringUtils.isEmpty(loginName))
        {
            msg = "用户名不能为空";
        }
        else if (StringUtils.isEmpty(password))
        {
            msg = "用户密码不能为空";
        }
        else if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            msg = "密码长度必须在5到20个字符之间";
        }
        else if (loginName.length() < UserConstants.USERNAME_MIN_LENGTH
                || loginName.length() > UserConstants.USERNAME_MAX_LENGTH)
        {
            msg = "账户长度必须在2到20个字符之间";
        }
        else if (!userService.checkLoginNameUnique(user))
        {
            msg = "注册'" + loginName + "'失败，注册账号已存在";
        }
        else {
            //获取用户邀请码，然后查询推广人，如果没有推广人，默认是系統
            String code = user.getCode();
            OpenaiDeptCode openaiDeptCode = new OpenaiDeptCode();
            openaiDeptCode.setCode(code);
            openaiDeptCode.setStatus(0);
            List<OpenaiDeptCode> openaiDeptCodes = openaiDeptCodeService.selectOpenaiDeptCodeList(openaiDeptCode);
            Long userId2 = 0L;
            if(openaiDeptCodes.size()>0){
                userId2 = openaiDeptCodes.get(0).getUserId();
            }

            user.setUpId(userId2);
            user.setPwdUpdateDate(DateUtils.getNowDate());
            user.setUserName(loginName);
            user.setSalt(ShiroUtils.randomSalt());
            user.setPassword(passwordService.encryptPassword(loginName, password, user.getSalt()));
            boolean regFlag = userService.registerUser(user);

            String freeTime = configService.selectConfigByKeyAndCompanyId(companyId,"free_time");
            String moneyTime = configService.selectConfigByKeyAndCompanyId(companyId,"money_time");
            String moneyTimeFour = configService.selectConfigByKeyAndCompanyId(companyId,"money_time_four");
            String mjFreeTime = configService.selectConfigByKeyAndCompanyId(companyId,"mj_free_time");
            String dalleTime = configService.selectConfigByKeyAndCompanyId(companyId,"dall_e_time");
            //新增openai_user_info 信息
            Long userId = user.getUserId();
            OpenaiUserInfo openaiUserInfo = new OpenaiUserInfo();
            openaiUserInfo.setUserId(user.getUserId());
            openaiUserInfo.setFreeTime(Long.parseLong(freeTime));
            openaiUserInfo.setMoneyTime(Long.parseLong(moneyTime));
            openaiUserInfo.setRechargeTime(new Date());
            openaiUserInfo.setExpirationTime(new Date());
            openaiUserInfo.setMjMoneyTime(Long.parseLong(mjFreeTime));
            openaiUserInfo.setDallETime(Long.parseLong(dalleTime));
            if(StringUtils.isNotEmpty(ip)){
                openaiUserInfo.setMoneyTimeFour(Long.parseLong(moneyTimeFour));
            }else{
                openaiUserInfo.setMoneyTimeFour(0L);
            }
            openaiUserInfo.setRechargeTimeFour(new Date());
            openaiUserInfo.setExpirationTimeFour(new Date());
            openaiUserInfo.setCreateBy(userId+"");
            openaiUserInfo.setCode(code);
            openaiUserInfo.setIp(ip);
            openaiUserInfo.setCompanyId(companyId);
            int row = openaiUserInfoService.insertOpenaiUserInfo(openaiUserInfo);

            OpenaiDeptCode openaiDeptCode2 = new OpenaiDeptCode();
            openaiDeptCode2.setUserId(openaiUserInfo.getUserId());
            openaiDeptCode2.setCode(MathUtils.generateRandomString(8));
            openaiDeptCodeService.insertOpenaiDeptCode(openaiDeptCode2);

            //如果找到了推广用户,即给用户分佣
            if(userId2!=0){
                String inviteMoneyStatus = configService.selectConfigByKeyAndCompanyId(companyId,"register_invite_money_status");
                if("0".equals(inviteMoneyStatus)){
                    String inviteMoney = configService.selectConfigByKeyAndCompanyId(companyId,"register_invite_money");
                    openaiUserInfoService.updateOpenaiUserInfoByBuyCount(userId2,new BigDecimal(inviteMoney));

                    //给推广员新增次数
                    String inviteMoneyTime = configService.selectConfigByKeyAndCompanyId(companyId,"register_invite_moneyTime");
                    openaiUserInfoService.updateOpenaiUserInfoByMoneyTime(userId2,Integer.parseInt(inviteMoneyTime));

                    OpenaiUserAccountLog openaiUserAccountLog = new OpenaiUserAccountLog();
                    openaiUserAccountLog.setType(0);
                    openaiUserAccountLog.setMoney(new BigDecimal(inviteMoney));
                    openaiUserAccountLog.setUserId(userId2);
                    openaiUserAccountLog.setRemark("用户 【"+loginName.substring(0,1)+"****"+loginName.substring(loginName.length()-1)+"】 注册成功,佣金是:"+inviteMoney);
                    openaiUserAccountLog.setCreateBy(openaiUserInfo.getUserId().toString());
                    openaiUserAccountLogService.insertOpenaiUserAccountLog(openaiUserAccountLog);
                }
            }

            if (!regFlag && row>0)
            {
                msg = "注册失败,请联系系统管理人员";
            }
            else
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(loginName, Constants.REGISTER, MessageUtils.message("user.register.success")));
            }
        }
        return msg;
    }
}
