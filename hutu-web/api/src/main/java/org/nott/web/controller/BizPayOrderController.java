package org.nott.web.controller;

import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.common.annotation.RedisCache;
import org.nott.common.utils.HutuUtils;
import org.nott.dto.UserSettleOrderDTO;
import org.nott.service.service.IBizPayOrderService;
import org.nott.vo.PayOrderVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author nott
 * @since 2024-05-24
 */
@RestController
@RequestMapping("/bizPayOrder")
public class BizPayOrderController {

    @Resource
    private IBizPayOrderService bizPayOrderService;

    @ApiOperation("用户下单")
    @PostMapping("settle")
    public ResponseEntity<?> settle(@RequestBody UserSettleOrderDTO userSettleOrderDTO){
        HutuUtils.requireNotNull(userSettleOrderDTO.isUseCoupon(),userSettleOrderDTO.getCouponId(),"优惠券id为空");
        HutuUtils.requireNotNull(userSettleOrderDTO.isUsePoint(),userSettleOrderDTO.getPointCount(),"使用积分为空");
        return ResponseEntity.successData(bizPayOrderService.doUserSettle(userSettleOrderDTO));
    }

    @ApiOperation("查询订单")
    @GetMapping("queryById/{id}")
    @RedisCache(key = "queryById",item = "#id",expire = 180)
    public ResponseEntity<?> queryById(@PathVariable("id") Long id){
        PayOrderVo vo = bizPayOrderService.queryPayOrderById(id);
        return ResponseEntity.successData(vo);
    }

}
