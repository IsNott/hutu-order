package org.nott.enums;

import lombok.Getter;

/**
 * 积分使用事件枚举
 * @author Nott
 * @date 2024-8-16
 */

@Getter
public enum UserPointUseEnum {
    USER_Active(0,"消费抵扣"),
    SYS_EXPIRED(1,"过期系统扣除");

    private Integer value;

    private String mark;

    UserPointUseEnum(Integer value, String mark) {
        this.value = value;
        this.mark = mark;
    }
}
