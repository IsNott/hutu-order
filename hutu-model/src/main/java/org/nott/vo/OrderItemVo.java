package org.nott.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.nott.dto.OrderItemDTO;

import java.io.Serializable;

/**
 * @author Nott
 * @date 2024-11-20
 */
@Data
@ApiModel("订单商品列表Vo对象")
public class OrderItemVo extends OrderItemDTO implements Serializable {

    private static final long serialVersionUID = 1L;
}
