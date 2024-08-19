package org.nott.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户-优惠券关系表
 * </p>
 *
 * @author nott
 * @since 2024-08-19
 */
@Getter
@Setter
@TableName("biz_user_coupon_relation")
public class BizUserCouponRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 优惠券id
     */
    private Long couponId;


}
