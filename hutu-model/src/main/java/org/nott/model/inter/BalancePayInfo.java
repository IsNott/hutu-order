package org.nott.model.inter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Nott
 * @date 2025-3-31
 */
@Data
@ApiModel("余额支付信息")
public class BalancePayInfo {

    @ApiModelProperty("实际支付金额")
    private BigDecimal actualDispose;

    @ApiModelProperty("赠送支付金额")
    private BigDecimal giftDispose;
}
