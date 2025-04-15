package org.nott.vo.pay;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author Nott
 * @date 2025-3-31
 */
@Data
@ApiModel("基础支付结果Vo")
public abstract class BaseResultVo {

    private String orderNo;
}
