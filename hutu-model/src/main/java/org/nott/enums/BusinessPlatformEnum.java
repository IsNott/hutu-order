package org.nott.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * 运行平台枚举
 */

@Getter
public enum BusinessPlatformEnum {

    WX_MINIPROGRAM("MP-WEIXIN"),
    ALIPAY_MINIPROGRAM("MP-ALIPAY"),
    WEB("WEB") ;

    private String name;

    BusinessPlatformEnum(String name) {
        this.name = name;
    }

    public static BusinessPlatformEnum getByName(String name){
        return Arrays.stream(BusinessPlatformEnum.values())
                .filter(r -> name.equalsIgnoreCase(r.getName()))
                .findAny()
                .orElse(null);
    }
}
