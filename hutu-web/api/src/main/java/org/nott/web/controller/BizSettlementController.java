package org.nott.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.common.utils.HutuUtils;
import org.nott.dto.UserSettleOrderDTO;
import org.nott.service.service.IBizPayOrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Nott
 * @date 2024-10-8
 */

@Api("用户结算")
@RestController
@RequestMapping("/bizSettle/")
public class BizSettlementController {

    @Resource
    private IBizPayOrderService bizPayOrderService;

    @ApiOperation("订单结算")
    @PostMapping("order")
    public ResponseEntity<?> order(@RequestBody UserSettleOrderDTO userSettleOrderDTO){
        HutuUtils.requireNotNull(userSettleOrderDTO.isUseCoupon(),userSettleOrderDTO.getCouponId(),"优惠券id为空");
        HutuUtils.requireNotNull(userSettleOrderDTO.isUsePoint(),userSettleOrderDTO.getPointCount(),"使用积分为空");
        return ResponseEntity.successData(bizPayOrderService.doUserSettle(userSettleOrderDTO));
    }

}
