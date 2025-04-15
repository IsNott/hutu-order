package org.nott.enums;

import lombok.Getter;

/**
 * @author Nott
 * @date 2025-3-31
 */
@Getter
public enum PayTypeEnum {

    QUERY(0, "查询"),
    PAY(1, "支付"),
    REFUND(2, "退款");

    private Integer val;

    private String desc;

    PayTypeEnum(Integer val, String desc) {
        this.val = val;
        this.desc = desc;
    }

}
