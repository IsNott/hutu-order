package org.nott.enums;

import lombok.Getter;

@Getter
public enum BusinessPlatformEnum {

    WX_MINIPROGRAM("MP-WEIXIN"),
    ALIPAY_MINIPROGRAM("MP-ALIPAY"),
    WEB("WEB") ;

    private String name;

    BusinessPlatformEnum(String name) {
        this.name = name;
    }
}
