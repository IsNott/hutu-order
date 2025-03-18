package org.nott.service.mapper.api;

import org.nott.model.BizCoupon;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 用户优惠券表 Mapper 接口
 * </p>
 *
 * @author nott
 * @since 2024-08-16
 */
public interface BizCouponMapper extends BaseMapper<BizCoupon> {

    List<BizCoupon> getCouponsByUserId(Long userId);
}
