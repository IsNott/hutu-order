package org.nott.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 优惠券规则表
 * </p>
 *
 * @author nott
 * @since 2024-08-16
 */
@Getter
@Setter
@TableName("biz_coupon_condition")
public class BizCouponCondition implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 优惠券id
     */
    private Long couponId;

    /**
     * 关联业务id
     */
    private Long bizId;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    /**
     * 是否启用
     */
    private Integer isEnable;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 创建者id
     */
    private Long creatorId;

    /**
     * 0-时间 1-满减 2-关联业务 3-全部
     */
    private Integer type;

    /**
     * 金额条件
     */
    private BigDecimal priceCondition;

    /**
     * 业务条件
     */
    private String bizCondition;

    /**
     * 条件表达式
     */
    private String conditionExpression;


}
