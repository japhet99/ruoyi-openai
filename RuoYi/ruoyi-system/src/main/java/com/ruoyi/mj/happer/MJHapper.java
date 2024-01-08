package com.ruoyi.mj.happer;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.mj.domain.*;
import com.ruoyi.system.service.ISysConfigService;
import net.sf.jsqlparser.util.deparser.CreateSequenceDeParser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class MJHapper {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ISysConfigService configService;

    /**
     * 创建任务
     * @param imagineDTO
     * @param companyId
     * @return
     * @throws Exception
     */
    public JSONObject createMjTask(ImagineDTO imagineDTO, Long companyId){
        JSONObject jsonObject = null;
        try {
            String midjourneyUrl = configService.selectConfigByKeyAndCompanyId(companyId,"midjourney_url");
            String MidjourneyKey = configService.selectConfigByKeyAndCompanyId(companyId,"midjourney_key");

            SubmitImagineDTO submitImagineDTO = new SubmitImagineDTO();
//            submitImagineDTO.setState(imagineDTO.getState());
            submitImagineDTO.setPrompt(imagineDTO.getPrompt());
            if (StringUtils.isNotBlank(imagineDTO.getFileName())) {
                submitImagineDTO.setBase64Array(Collections.singletonList(imagineDTO.getFileName()));
            }
//            String rspStr = HttpUtil.post(midjourneyUrl + "mj/submit/imagine", JSONObject.toJSONString(submitImagineDTO));

            String rspStr = HttpRequest.post(midjourneyUrl + "mj/submit/imagine").header("Authorization", MidjourneyKey).body(JSONObject.toJSONString(submitImagineDTO)).execute().body();
            log.info("rspStr=="+rspStr);
            jsonObject = JSONObject.parseObject(rspStr);

        }catch (Exception e){
            log.error("createMjTask error imagineDTO={}",imagineDTO,e);
        }
        return jsonObject;
    }


    /**
     * 指定ID获取任务
     */
    public JSONObject getMjByTaskId(String taskId, Long companyId){
        JSONObject jsonObject = null;
        try {
            String midjourneyUrl = configService.selectConfigByKeyAndCompanyId(companyId,"midjourney_url");
            String MidjourneyKey = configService.selectConfigByKeyAndCompanyId(companyId,"midjourney_key");

            String rspStr = HttpRequest.get(midjourneyUrl + "mj/task/"+taskId+"/fetch").header("Authorization", MidjourneyKey).execute().body();
            jsonObject = JSONObject.parseObject(rspStr);
            return jsonObject;
        }catch (Exception e){
            log.error("getMjByTaskId error taskId={}",taskId,e);
        }
        return null;
    }


    /**
     * 修改任务
     * @param changeDTO
     * @param companyId
     * @return
     * @throws Exception
     */
    public JSONObject changeMjTask(ChangeDTO changeDTO, Long companyId){
        JSONObject jsonObject = null;
        try {
            String midjourneyUrl = configService.selectConfigByKeyAndCompanyId(companyId,"midjourney_url");
            String MidjourneyKey = configService.selectConfigByKeyAndCompanyId(companyId,"midjourney_key");
            JSONObject jsonObject2 = new JSONObject();
            jsonObject2.put("action",changeDTO.getAction());
            jsonObject2.put("index",changeDTO.getIndex());
            jsonObject2.put("notifyHook",changeDTO.getNotifyHook());
            jsonObject2.put("state",changeDTO.getState());
            jsonObject2.put("taskId",changeDTO.getTaskId());

//            String rspStr = HttpUtil.post(midjourneyUrl + "mj/submit/change", jsonObject2.toJSONString());
            String rspStr = HttpRequest.post(midjourneyUrl + "mj/submit/change").header("Authorization", MidjourneyKey).body(jsonObject2.toJSONString()).execute().body();

            jsonObject = JSONObject.parseObject(rspStr);

        }catch (Exception e){
            log.error("changeMjTask error changeDTO={}",changeDTO,e);
        }
        return jsonObject;
    }


    /**
     * 修改任务
     * @param submitBlendDTO
     * @param companyId
     * @return
     * @throws Exception
     */
    public String blendMjTask(SubmitBlendDTO submitBlendDTO, Long companyId){
        try {
            String midjourneyUrl = configService.selectConfigByKeyAndCompanyId(companyId,"midjourney_url");

            String rspStr = HttpUtil.post(midjourneyUrl + "mj/submit/blend", JSONObject.toJSONString(submitBlendDTO));

            JSONObject jsonObject = JSONObject.parseObject(rspStr);
            if(jsonObject.getString("code").equals("1")){
                return jsonObject.getString("result");
            }else if(jsonObject.getString("code").equals("21")){

            }else if(jsonObject.getString("code").equals("22")){

            }else if(jsonObject.getString("code").equals("other")){

            }

        }catch (Exception e){
            log.error("blendMjTask error submitBlendDTO={}",submitBlendDTO,e);
        }
        return null;
    }

}
