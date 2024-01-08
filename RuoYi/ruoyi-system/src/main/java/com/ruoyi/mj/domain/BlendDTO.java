package com.ruoyi.mj.domain;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class BlendDTO {

    /*垫图base64数组（传参 到mj）*/
    private List<String> base64Array;

    /*比例: PORTRAIT(2:3); SQUARE(1:1); LANDSCAPE(3:2),
    可用值:PORTRAIT,SQUARE,LANDSCAPE,示例值(SQUARE)*/
    private String dimensions;

    /**
     * state: 自定义参数, task中保留.
     */
    private String state;

    /*回调地址, 为空时使用全局notifyHook*/
    private String notifyHook;


    /*垫图 （用户上传）*/
    private MultipartFile file;
    private MultipartFile file2;

    /*用户上传的图进行保存*/
    private String fileName;
}
