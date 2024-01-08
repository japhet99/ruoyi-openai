package com.ruoyi.openai.mapper;

import com.ruoyi.openai.domain.OpenaiUserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 用户信息详情Mapper接口
 * 
 * @author ruoyi
 * @date 2023-05-23
 */
public interface OpenaiUserInfoMapper 
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
     * 删除用户信息详情
     * 
     * @param id 用户信息详情主键
     * @return 结果
     */
    public int deleteOpenaiUserInfoById(Long id);

    /**
     * 批量删除用户信息详情
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOpenaiUserInfoByIds(String[] ids);

    /**
     * 根据userId修改 moneyTime
     * @param userId
     * @return
     */
    int updateOpenaiUserInfoByMoneyTime( @Param("moneyTime") Integer moneyTime,@Param("userId") Long userId);

    /**
     * 根据userId修改 FreeTime
     * @param userId
     * @return
     */
    int updateOpenaiUserInfoByFreeTime(@Param("freeTime")Integer freeTime,@Param("userId")Long userId);


    /**
     * 根据userId修改 mjmoneyTime
     * @param userId
     * @return
     */
    int updateOpenaiUserInfoByMJMoneyTime( @Param("mjMoneyTime") Integer mjMoneyTime,@Param("userId") Long userId);

    /**
     * 根据userId修改 mjFreeTime
     * @param userId
     * @return
     */
    int updateOpenaiUserInfoByMJFreeTime(@Param("mjFreeTime")Integer mjFreeTime,@Param("userId")Long userId);


    /**
     * 根据userId修改 4.0 moneyTime
     * @param userId
     * @return
     */
    int updateOpenaiUserInfoByMoneyTimeFour( @Param("moneyTime") Integer moneyTime,@Param("userId") Long userId);

    /**
     *
     * @param userId
     * @return
     */
    OpenaiUserInfo selectOpenaiUserInfoByUserId(Long userId);

    OpenaiUserInfo selectUserByIp(String ip);

    /**
     * 修改 佣金金额
     * @param userId
     * @param inviteMoney
     * @return
     */
    int updateOpenaiUserInfoByMoney(@Param("userId")Long userId, @Param("inviteMoney")BigDecimal inviteMoney);

    int updateOpenaiUserInfoByBuyCount(@Param("userId")Long userId, @Param("inviteMoney") BigDecimal inviteMoney);

    /**
     * 查询传入时间范围内的每天的用户注册人数
     * @param companyId
     * @param begin
     * @param end
     * @return
     */
    List<Map<String,Object>> selectOpenaiUserInfoListByPartner(@Param("companyId")Long companyId,@Param("begin")String begin,@Param("end")String end);


    /**
     * 查询传入时间范围内的每天的订单数
     * @param companyId
     * @param begin
     * @param end
     * @return
     */
    List<Map<String,Object>> selectOpenaiOrderInfoListByPartner(@Param("companyId")Long companyId,@Param("begin")String begin,@Param("end")String end);

    /**
     * 查询传入时间范围内的每天的聊天活跃数
     * @param companyId
     * @param begin
     * @param end
     * @return
     */
    List<Map<String,Object>> selectOpenaiActiveInfoListByPartner(@Param("companyId")Long companyId,@Param("begin")String begin,@Param("end")String end);

    @Select("select count(id) from openai_user_info where company_id = #{companyId}")
    Integer selectOpenaiUserInfoCountByPartner(Long companyId);

    @Select("select count(id) from openai_chat_order where company_id = #{companyId}")
    Integer selectOpenaiOrderCountByPartner(Long companyId);

    @Select("select IFNULL(sum(pay_price),0) from openai_chat_order where status = 1 and company_id = #{companyId}")
    Integer selectOpenaiOrderSuccessCountByPartner(Long companyId);

    int updateOpenaiUserInfoByDallETime(@Param("dallETime")Integer dallETime,@Param("userId")Long userId);
}
