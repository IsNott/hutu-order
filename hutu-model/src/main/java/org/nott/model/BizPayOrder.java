package org.nott.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户订单表
 * </p>
 *
 * @author nott
 * @since 2024-05-24
 */
@Getter
@Setter
@TableName("biz_pay_order")
public class BizPayOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 0-单点 1-套餐
     */
    private Integer orderType;

    /**
     * 0-堂食 1-打包 2-外送
     */
    private Integer pickType;

    /**
     * 总金额
     */
    private String totalAmount;

    /**
     * 菜品信息
     */
    private String itemInfo;

    /**
     * 支付方式id
     */
    private Long paywayId;

    /**
     * 外部交易流水号
     */
    private String outTradeNo;

    /**
     * 退款交易流水号
     */
    private String refundOutTradeNo;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 0-初始化 1-进行中 2-支付 3-退单
     */
    private Integer orderStatus;

    /**
     * 支付业务通知信息
     */
    private String payNotifyMsg;

    /**
     * 退款业务通知信息
     */
    private String refundNotifyMsg;

    /**
     * 订单备注
     */
    private String remark;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;


}
