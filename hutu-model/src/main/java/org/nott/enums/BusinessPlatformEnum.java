package org.nott.enums;

import lombok.Getter;

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
}
