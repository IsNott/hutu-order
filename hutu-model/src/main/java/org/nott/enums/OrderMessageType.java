package org.nott.enums;

import io.swagger.annotations.ApiModel;
import lombok.Getter;

/**
 * 订单消息类型枚举
 */
@Getter
public enum OrderMessageType {

    /**
     * 下单
     */
    IN("in"),
    /**
     * 完成
     */
    FINISH("finish"),
    /**
     * 用户取餐
     */
    TAKED("taked"),
    ;

    private String val;

    OrderMessageType(String val) {
        this.val = val;
    }
}
