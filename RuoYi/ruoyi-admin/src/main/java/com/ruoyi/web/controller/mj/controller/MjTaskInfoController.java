package com.ruoyi.web.controller.mj.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.mj.domain.*;
import com.ruoyi.mj.enums.DrawEnum;
import com.ruoyi.mj.happer.MJHapper;
import com.ruoyi.mj.service.IMjTaskInfoService;
import com.ruoyi.openai.domain.OpenaiUserInfo;
import com.ruoyi.openai.mapper.OpenaiUserInfoMapper;
import com.ruoyi.openai.service.IOpenaiIndexService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api/mj")
public class MjTaskInfoController extends BaseController {

}

