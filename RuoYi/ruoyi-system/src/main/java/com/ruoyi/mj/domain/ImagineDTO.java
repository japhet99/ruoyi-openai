package com.ruoyi.mj.domain;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ImagineDTO {

    /*自定义参数*/
    private String state;

    /*提示词,示例值(Cat)	*/
    private String prompt;

    /*垫图 （用户上传）*/
    private MultipartFile file;
    /*用户上传的图进行保存*/
    private String fileName;
    /*垫图base64数组（传参 到mj）*/
    private List<String> base64Array;

    /*回调地址, 为空时使用全局notifyHook*/
    private String notifyHook;


    /**
     * 是否携带参数
     * 0：不带
     * 1：带
     */
    private Integer param;

    /**
     * 版本  5  5.1  5.2
     */
    private String v;

    /**
     * 风格化  0-1000
     */
    private String s;

    /**
     * 图片尺寸
     * 1:1
     * 4:3
     * 3:4
     * 16:9
     * 9:16
     */
    private String ar;

    /**
     * 混乱
     * 0 - 100
     */
    private String c;


    /**
     * 像素
     * 1：高清
     * 2：超清
     */
    private String q;

}
