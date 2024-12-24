package org.nott.model.inter;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author Nott
 * @date 2024-12-24
 */
@Data
@ApiModel("订单消息信息")
public class OrderWsMessageInfo extends BaseJsonInfo{

    @ApiModelProperty("消息类型")
    private String messageType;

    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("菜品统计")
    private Integer itemPiece;
    @ApiModelProperty("预计等待时间")
    private Integer waitTime;
    @ApiModelProperty("0-单点 1-套餐")
    private Integer orderType;
    @ApiModelProperty("0-堂食 1-打包 2-外送")
    private Integer pickType;
    @ApiModelProperty("订单号")
    private String orderNo;
    @ApiModelProperty("订单备注")
    private String remark;
    @ApiModelProperty("支付时间")
    private Date settleTime;
    @ApiModelProperty("店铺id")
    private Long shopId;

}
