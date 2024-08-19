package org.nott.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.service.service.IBizCouponService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 用户优惠券表 前端控制器
 * </p>
 *
 * @author nott
 * @since 2024-08-16
 */
@Api("用户优惠券")
@RestController
@RequestMapping("/bizCoupon")
public class BizCouponController {

    @Resource
    private IBizCouponService bizCouponService;

    @PostMapping("myCoupon")
    @ApiOperation("查询当前用户的优惠券")
    public ResponseEntity<?> myCoupon(){
        return ResponseEntity.successData(bizCouponService.queryAvailableCoupon());
    }

}
