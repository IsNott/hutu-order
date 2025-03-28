package org.nott.service.api;

import org.nott.model.BizCoupon;
import com.baomidou.mybatisplus.extension.service.IService;
import org.nott.vo.UserCouponVo;

import java.util.List;

/**
 * <p>
 * 用户优惠券表 服务类
 * </p>
 *
 * @author nott
 * @since 2024-08-16
 */
public interface IBizCouponService extends IService<BizCoupon> {

    List<UserCouponVo> queryAvailableCoupon();

    List<BizCoupon> findCouponsByUserId(Long userId);
}
