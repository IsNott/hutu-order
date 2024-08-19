package org.nott.enums;

import lombok.Getter;

/**
 * 优惠券条件类型枚举, 0-时间 1-满减 2-关联业务 3-全部
 * @author Nott
 * @date 2024-8-19
 */

@Getter
public enum CouponConditionTypeEnum {

    TIME(0),
    FULL_DISCOUNT(1),
    BIZ_REL(2),
    ALL(3);

    private Integer type;

    CouponConditionTypeEnum(Integer type) {
        this.type = type;
    }
}
