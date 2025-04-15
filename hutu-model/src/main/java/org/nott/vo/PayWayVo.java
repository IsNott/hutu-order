package org.nott.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Nott
 * @date 2024-6-26
 */

@Data
@ApiModel("支付方式VO")
public class PayWayVo {

    @ApiModelProperty("支付方式id")
    private Long id;

    @ApiModelProperty("名称")
    private String paymentName;

    @ApiModelProperty("展示顺序")
    private Integer displayOrder;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("是否收起")
    private Integer packUp;

    @ApiModelProperty("支付code")
    private String payCode;

}
