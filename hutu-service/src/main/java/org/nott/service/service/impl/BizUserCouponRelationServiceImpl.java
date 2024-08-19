package org.nott.service.service.impl;

import org.nott.model.BizCoupon;
import org.nott.model.BizUserCouponRelation;
import org.nott.service.mapper.BizUserCouponRelationMapper;
import org.nott.service.service.IBizUserCouponRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户-优惠券关系表 服务实现类
 * </p>
 *
 * @author nott
 * @since 2024-08-19
 */
@Service
public class BizUserCouponRelationServiceImpl extends ServiceImpl<BizUserCouponRelationMapper, BizUserCouponRelation> implements IBizUserCouponRelationService {

}
