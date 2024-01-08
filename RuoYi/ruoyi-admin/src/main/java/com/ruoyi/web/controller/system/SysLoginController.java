package com.ruoyi.web.controller.system;

import com.github.pagehelper.PageHelper;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.service.ConfigService;
import com.ruoyi.openai.domain.OpenaiChatOrder;
import com.ruoyi.openai.domain.OpenaiChatPlan;
import com.ruoyi.openai.domain.OpenaiUserInfo;
import com.ruoyi.openai.service.IOpenaiChatOrderService;
import com.ruoyi.openai.service.IOpenaiChatPlanService;
import com.ruoyi.openai.service.IOpenaiOrderLogService;
import com.ruoyi.openai.service.IOpenaiUserInfoService;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.system.service.ISysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 登录验证
 * 
 * @author ruoyi
 */
@Controller
public class SysLoginController extends BaseController
{
    /**
     * 是否开启记住我功能
     */
    @Value("${shiro.rememberMe.enabled: false}")
    private boolean rememberMe;

    @Autowired
    private ConfigService configService;
    @Autowired
    private ISysDictDataService sysDictDataService;
    @Autowired
    private IOpenaiUserInfoService openaiUserInfoService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private IOpenaiChatOrderService openaiChatOrderService;
    @Autowired
    private IOpenaiChatPlanService openaiChatPlanService;

    @Value("${ruoyi.socketIoUrl}")
    private String socketIoUrl;

    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response, ModelMap mmap)
    {
        // 如果是Ajax请求，返回Json字符串。
        if (ServletUtils.isAjaxRequest(request))
        {
            return ServletUtils.renderString(response, "{\"code\":\"1\",\"msg\":\"未登录或登录超时。请重新登录\"}");
        }
        // 是否开启记住我
        mmap.put("isRemembered", rememberMe);
        // 是否开启用户注册
        mmap.put("isAllowRegister", Convert.toBool(configService.getKey("sys.account.registerUser"), false));
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public AjaxResult ajaxLogin(String username, String password, Boolean rememberMe, String prefix)
    {

        if(StringUtils.isEmpty(prefix)){
            return error("域名不能为空!");
        }

        //判断域名是否有效
        SysDictData dictData = new SysDictData();
        dictData.setDictType("partner");
        dictData.setStatus("0");
        List<SysDictData> sysDictData = sysDictDataService.selectDictDataList(dictData);
        boolean prefixStatus = false;
        for (SysDictData sysDictDatum : sysDictData) {
            if(prefix.equals(sysDictDatum.getDictLabel())){
                prefixStatus = true;
                break;
            }
        }
        if(!prefixStatus){
            return error("域名错误!");
        }

        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        Subject subject = SecurityUtils.getSubject();
        try
        {
            subject.login(token);
            return success();
        }
        catch (AuthenticationException e)
        {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage()))
            {
                msg = e.getMessage();
            }
            return error(msg);
        }
    }

    @GetMapping("/unauth")
    public String unauth()
    {
        return "error/unauth";
    }

    /**
     * 免责声明
     * @return
     */
    @GetMapping("/agreement")
    public String agreement()
    {
        return "agreement";
    }

    /**
     * 客服页面
     * @return
     */
    @GetMapping("/kefu")
    public String kefu()
    {
        return "kefu";
    }

    /**
     * 客服
     * @return
     */
    @PostMapping("/kf")
    @ResponseBody
    public AjaxResult getKeFu(String prefix)
    {

        if(StringUtils.isEmpty(prefix)){
            return error("域名不能为空!");
        }

        //判断域名是否有效
        SysDictData dictData = new SysDictData();
        dictData.setDictType("partner");
        dictData.setStatus("0");
        List<SysDictData> sysDictData = sysDictDataService.selectDictDataList(dictData);
        boolean prefixStatus = false;
        Long companyId = 1L;
        for (SysDictData sysDictDatum : sysDictData) {
            if(prefix.equals(sysDictDatum.getDictLabel())){
                prefixStatus = true;
                companyId = Long.parseLong(sysDictDatum.getDictValue());
                break;
            }
        }
        if(!prefixStatus){
            return error("域名错误!");
        }

        SysDictData dictData2 = new SysDictData();
        dictData2.setDictType("kefu");
        dictData2.setStatus("0");
        dictData2.setCompanyId(companyId);

        List<SysDictData> sysDictData2 = sysDictDataService.selectDictDataList(dictData2);
        for (SysDictData data : sysDictData2) {
            data.setCompanyId(null);
        }
        return AjaxResult.success(sysDictData2);
    }


    @RequiresPermissions("openai:openai:view")
    @GetMapping("/chat")
    public String chat(ModelMap mmap) {
        // 取身份信息
        SysUser user = getSysUser();
        if (user == null) {
            throw new RuntimeException("服务器超时，请重新登录");
        }

        OpenaiUserInfo openaiUserInfo = openaiUserInfoService.selectOpenaiUserInfoByUserId(user.getUserId());
        mmap.put("freeTime", openaiUserInfo.getFreeTime());
        mmap.put("moneyTime", openaiUserInfo.getMoneyTime());
        mmap.put("moneyTimeFour", openaiUserInfo.getMoneyTimeFour());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mmap.put("rechargeTime", simpleDateFormat.format(openaiUserInfo.getRechargeTime()));
        mmap.put("expirationTime", simpleDateFormat.format(openaiUserInfo.getExpirationTime()));
        mmap.put("rechargeTimeFour", simpleDateFormat.format(openaiUserInfo.getRechargeTimeFour()));
        mmap.put("expirationTimeFour", simpleDateFormat.format(openaiUserInfo.getExpirationTimeFour()));
        mmap.put("mjFreeTime", openaiUserInfo.getMjFreeTime());
        mmap.put("mjMoneyTime", openaiUserInfo.getMjMoneyTime());
        mmap.put("dallETime", openaiUserInfo.getDallETime());
        mmap.put("socketIoUrl", socketIoUrl);
        mmap.put("version", RuoYiConfig.getVersion());
        mmap.put("user", user);

        return "openai/openai4";
    }

    /**
     * 价格
     * @return
     */
    @GetMapping("/price")
    public String price()
    {
        return "openai/price";
    }

    /**
     * init
     * @return
     */
    @GetMapping("/init")
    public String init()
    {
        return "openai/init";
    }

    /**
     * bottom_input
     * @return
     */
    @GetMapping("/bottom_input")
    public String bottom_input()
    {
        return "openai/bottom_input";
    }


    /**
     * promote
     * @return
     */
    @GetMapping("/promote")
    public String promote()
    {
        return "openai/promote";
    }

    /**
     *
     * @return
     */
    @GetMapping("/mj")
    public String mj() {
        return "redirect:mj.html";
    }

    /**
     *
     * @return
     */
    @GetMapping("/dalle3")
    public String dalle() {
        return "redirect:dalle3.html";
    }

    /**
     * 合伙人的首页
     * partner
     * @return
     */
    @RequiresPermissions("system:partner:view")
    @GetMapping("/partner")
    public String partner()
    {
        return "partner/partner";
    }


    /**
     * 合伙人的数据表格图
     * @param request
     * @return
     */
    @RequiresPermissions("system:partner:list")
    @PostMapping("/partner/data")
    @ResponseBody
    public AjaxResult partnerData(HttpServletRequest request)
    {
        SysUser sysUser = getSysUser();
        if (sysUser == null) {
            throw new RuntimeException("服务器超时，请重新登录");
        }
        OpenaiUserInfo openaiUserInfo1 = openaiUserInfoService.selectOpenaiUserInfoByUserId(sysUser.getUserId());
        Map<String,Object> map = new HashMap<>();
        String begin = request.getParameter("begin");
        String end = request.getParameter("end");
        long manyMonth = DateUtils.getManyMonth(begin, end);
        if (manyMonth > 12) {
            return AjaxResult.error("查询时间不能大于1年");
        }
        //总共多少人
        Integer userInfoCount = openaiUserInfoService.selectOpenaiUserInfoCountByPartner(openaiUserInfo1.getCompanyId());
        //总共多少人
        Integer orderCount = openaiUserInfoService.selectOpenaiOrderCountByPartner(openaiUserInfo1.getCompanyId());
        //总成功订单金额
        Integer orderSuccessCount = openaiUserInfoService.selectOpenaiOrderSuccessCountByPartner(openaiUserInfo1.getCompanyId());

        map.put("userInfoCount",userInfoCount);
        map.put("orderCount",orderCount);
        map.put("orderSuccessCount",orderSuccessCount);

        //指定时间范围查询注册人数
        List<Map<String,Object>> everyDayRegister = openaiUserInfoService.selectOpenaiUserInfoListByPartner(openaiUserInfo1.getCompanyId(),begin,end);
        map.put("everyDayRegister",everyDayRegister);
        //指定时间范围查询订单数
        List<Map<String,Object>> everyDayOrder = openaiUserInfoService.selectOpenaiOrderInfoListByPartner(openaiUserInfo1.getCompanyId(),begin,end);
        map.put("everyDayOrder",everyDayOrder);
        //指定时间范围查询活跃数
        List<Map<String,Object>> everyDayActive = openaiUserInfoService.selectOpenaiActiveInfoListByPartner(openaiUserInfo1.getCompanyId(),begin,end);
        map.put("everyDayActive",everyDayActive);
        return AjaxResult.success(map);
    }



    /**
     * 用户信息列表
     * partner
     * @return
     */
    @RequiresPermissions("system:partner:view")
    @GetMapping("/partner/userInfo")
    public String userInfo()
    {
        return "partner/userInfo";
    }


    /**
     * 合伙人的用户数据表
     * @param request
     * @return
     */
    @RequiresPermissions("system:partner:list")
    @RequestMapping("/partner/userInfoList")
    @ResponseBody
    public Map<String,Object> userInfoList(HttpServletRequest request) {
        SysUser sysUser = getSysUser();
        if (sysUser == null) {
            throw new RuntimeException("服务器超时，请重新登录");
        }
        Map<String,Object> resMap = new HashMap<>();
        OpenaiUserInfo openaiUserInfo1 = openaiUserInfoService.selectOpenaiUserInfoByUserId(sysUser.getUserId());

        OpenaiUserInfo openaiUserInfo = new OpenaiUserInfo();
        openaiUserInfo.setCompanyId(openaiUserInfo1.getCompanyId());
        Integer pageNo = Integer.valueOf(request.getParameter("page"));
        Integer pageSize = Integer.valueOf(request.getParameter("limit"));

        PageHelper.orderBy("id desc");
        PageHelper.startPage(pageNo, pageSize );
        List<OpenaiUserInfo> openaiUserInfos = openaiUserInfoService.selectOpenaiUserInfoList(openaiUserInfo);
        List<Map<String,Object>> listMap = new ArrayList<>();
        for (OpenaiUserInfo userInfo : openaiUserInfos) {
            Map<String,Object> map = new HashMap<>();
            SysUser sysUser1 = sysUserService.selectUserById(userInfo.getUserId());
            if(sysUser1==null){
                sysUser1 = new SysUser();
            }
            map.put("name",maskUsername(sysUser1.getLoginName()));
            map.put("free35",userInfo.getFreeTime());
            map.put("chatgpt35",userInfo.getMoneyTime());
            map.put("chatgpt40",userInfo.getMoneyTimeFour());
            map.put("createTime",sysUser1.getCreateTime());
            listMap.add(map);
        }
        TableDataInfo dataTable = getDataTable(openaiUserInfos);
        resMap.put("data",listMap);
        resMap.put("code",0);
        resMap.put("count",dataTable.getTotal());
        resMap.put("msg","操作成功");
        return resMap;
    }

    /**
     * 订单信息列表
     * partner
     * @return
     */
    @RequiresPermissions("system:partner:view")
    @GetMapping("/partner/orderInfo")
    public String orderInfo()
    {
        return "partner/orderInfo";
    }


    /**
     * 合伙人的订单数据表
     * @param request
     * @return
     */
    @RequiresPermissions("system:partner:list")
    @RequestMapping("/partner/orderList")
    @ResponseBody
    public Map<String,Object> orderList(HttpServletRequest request) {
        SysUser sysUser = getSysUser();
        if (sysUser == null) {
            throw new RuntimeException("服务器超时，请重新登录");
        }
        Map<String,Object> resMap = new HashMap<>();
        OpenaiUserInfo openaiUserInfo1 = openaiUserInfoService.selectOpenaiUserInfoByUserId(sysUser.getUserId());

        OpenaiChatOrder openaiChatOrder = new OpenaiChatOrder();
        openaiChatOrder.setCompanyId(openaiUserInfo1.getCompanyId());
        Integer pageNo = Integer.valueOf(request.getParameter("page"));
        Integer pageSize = Integer.valueOf(request.getParameter("limit"));

        PageHelper.orderBy("id desc");
        PageHelper.startPage(pageNo, pageSize );
        List<OpenaiChatOrder> openaiChatOrders = openaiChatOrderService.selectOpenaiChatOrderList(openaiChatOrder);
        List<Map<String,Object>> listMap = new ArrayList<>();

        for (OpenaiChatOrder chatOrder : openaiChatOrders) {
            Map<String,Object> map = new HashMap<>();
            SysUser sysUser1 = sysUserService.selectUserById(chatOrder.getUserId());
            map.put("name",maskUsername(sysUser1.getLoginName()));
            map.put("planName",openaiChatPlanService.selectOpenaiChatPlanById(chatOrder.getPlanId()).getName());
            map.put("status",chatOrder.getStatus()==0?"待支付":chatOrder.getStatus()==1?"完成":chatOrder.getStatus()==2?"过期":"关闭");
            map.put("createTime",sysUser1.getCreateTime());
            listMap.add(map);
        }

        TableDataInfo dataTable = getDataTable(openaiChatOrders);
        resMap.put("data",listMap);
        resMap.put("code",0);
        resMap.put("count",dataTable.getTotal());
        resMap.put("msg","操作成功");
        return resMap;
    }

    /**
     * 用户名称脱敏
     * @param username
     * @return
     */
    public static String maskUsername(String username) {
        if (username!=null && username.length() >= 5) {
            String prefix = username.substring(0, 3);
            String suffix = "";
            for (int i = 0; i < username.length()-3; i++) {
                suffix+="*";
            }
            String masked = prefix +  suffix;
            return masked;
        } else {
            return "****";
        }
    }
}
