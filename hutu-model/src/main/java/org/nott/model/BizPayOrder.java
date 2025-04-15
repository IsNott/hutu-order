package org.nott.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
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
     * 支付订单id
     */
    private Long payOrderId;

    /**
     * 0-单点 1-套餐
     */
    private Integer orderType;

    /**
     * 0-堂食 1-打包 2-外送
     */
    private Integer pickType;

    /**
     * 1-支付 2-退款
     */
    private Integer type;

    /**
     * 总金额
     */
    private BigDecimal totalAmount;

    /**
     * 原金额
     */
    private BigDecimal originAmount;

    /**
     * 菜品信息
     */
    private String itemInfo;

    /**
     * 菜品统计
     */
    private Integer itemPiece;

    /**
     * 预计等待时间
     */
    private Integer waitTime;

    /**
     * 支付方式code
     */
    private String payCode;

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
     * 退款订单号
     */
    private String refundOrderNo;

    /**
     * 店内用餐号
     */
    private String shopOrderNo;

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

    /**
     * 支付时间
     */
    private Date settleTime;

    /**
     * 店铺id
     */
    private Long shopId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户删除标识
     */
    private Integer userDelFlag;


}
