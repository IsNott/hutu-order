package org.nott.model.inter;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Nott
 * @date 2025-3-31
 */
@Data
public class BalanceRefundInfo {

    private BigDecimal actualRefund;

    private BigDecimal giftRefund;
}
