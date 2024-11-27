package org.nott.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * @author Nott
 * @date 2024-11-18
 */
@Getter
public enum PickTypeEnum {
    EAT_IN(0),
    TACK_OUT(1);


    private Integer val;

    PickTypeEnum(Integer val) {
        this.val = val;
    }

    public static PickTypeEnum getByVal(Integer val){
        return Arrays.stream(PickTypeEnum.values()).filter(r -> r.getVal().equals(val))
                .findFirst().orElse(null);
    }
}
