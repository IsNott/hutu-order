package org.nott.service.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.nott.common.utils.HutuUtils;
import org.nott.enums.YesOrNoEnum;
import org.nott.model.BizCoupon;
import org.nott.model.BizCouponCondition;
import org.nott.service.mapper.BizCouponMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.nott.service.service.IBizCouponConditionService;
import org.nott.service.service.IBizCouponService;
import org.nott.vo.UserCouponVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户优惠券表 服务实现类
 * </p>
 *
 * @author nott
 * @since 2024-08-16
 */
@Service
public class BizCouponServiceImpl extends ServiceImpl<BizCouponMapper, BizCoupon> implements IBizCouponService {

    @Resource
    private BizCouponMapper bizCouponMapper;

    @Resource
    private IBizCouponConditionService bizCouponConditionService;

    @Override
    public List<UserCouponVo> queryAvailableCoupon() {
        long userId = StpUtil.getLoginIdAsLong();
        List<BizCoupon> couponList = this.findCouponsByUserId(userId);
        if (couponList.isEmpty()) {
            return Collections.emptyList();
        }

        ArrayList<UserCouponVo> vos = new ArrayList<>();

        List<Long> conditionIds = couponList.stream()
                .filter(r -> HutuUtils.isNotEmpty(r.getConditionId()))
                .map(BizCoupon::getConditionId)
                .collect(Collectors.toList());
        LambdaQueryWrapper<BizCouponCondition> wrapper = new LambdaQueryWrapper<BizCouponCondition>()
                .in(BizCouponCondition::getId, conditionIds)
                .eq(BizCouponCondition::getIsEnable, YesOrNoEnum.YES.getValue());

        List<BizCouponCondition> couponConditions = bizCouponConditionService.list(wrapper);
        Map<Long, BizCouponCondition> conditionMap = couponConditions.stream().filter(Objects::nonNull)
                .collect(Collectors.toMap(BizCouponCondition::getId, element -> element, (k1, k2) -> k1));
        for (BizCoupon coupon : couponList) {
            Long conditionId = coupon.getConditionId();
            if (conditionMap.containsKey(conditionId)) {
                BizCouponCondition bizCouponCondition = conditionMap.get(conditionId);
                UserCouponVo vo = HutuUtils.transToObject(coupon, UserCouponVo.class);
                vo.setCondition(bizCouponCondition);
                vos.add(vo);
            }
        }

        return vos;
    }


    @Override
    public List<BizCoupon> findCouponsByUserId(Long userId) {
        return bizCouponMapper.getCouponsByUserId(userId);
    }
}
