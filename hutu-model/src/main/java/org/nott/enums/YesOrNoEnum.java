package org.nott.enums;

import lombok.Getter;

/**
 * @author Nott
 * @date 2024-8-19
 */
@Getter
public enum YesOrNoEnum {
    YES(1),
    NO(0)
    ;

    YesOrNoEnum(Integer value) {
        this.value = value;
    }

    private Integer value;

}
