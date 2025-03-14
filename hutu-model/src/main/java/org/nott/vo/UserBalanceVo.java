package org.nott.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Nott
 * @date 2025-3-14
 */
@Data
public class UserBalanceVo {

    private BigDecimal actualBalance;

    private BigDecimal giftBalance;

    private BigDecimal total;

    public void total(){
        this.total = this.actualBalance.add(this.giftBalance);
    }
}
