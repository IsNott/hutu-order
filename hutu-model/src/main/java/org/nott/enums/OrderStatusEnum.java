package org.nott.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum OrderStatusEnum {

    INIT(0),
    PROCESSING(1),
    PAYED(2),
    REFUND(3),
    EXPIRE(4),
    FAILED(5);

    private Integer val;

    OrderStatusEnum(Integer val) {
        this.val = val;
    }

    public static PickTypeEnum getByVal(Integer val){
        return Arrays.stream(PickTypeEnum.values()).filter(r -> r.getVal().equals(val))
                .findFirst().orElse(null);
    }


}
