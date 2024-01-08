package com.ruoyi.mj.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * 绘画枚举
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum DrawEnum {

    IMAGINE("IMAGINE", 0),
    VARIATION("VARIATION", 1),
    UPSCALE("UPSCALE", 2),
    RESET("RESET", 3);
    private String desc;
    private Integer code;



    /**
     * 根据描述获取code
     * @param desc
     * @return
     */
    public static Integer getDescByUserLevel(String desc){
        for (DrawEnum value : values()) {
            if(Objects.equals(value.getDesc(),desc)){
                return value.getCode();
            }
        }
        return 0;
    }
}
