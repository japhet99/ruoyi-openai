package com.ruoyi.mj.domain;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class SubmitImagineDTO {

    /*自定义参数*/
    private String state;

    /*提示词,示例值(Cat)	*/
    private String prompt;

    /*垫图base64数组（传参 到mj）*/
    private List<String> base64Array;

    /*回调地址, 为空时使用全局notifyHook*/
    private String notifyHook;
}
