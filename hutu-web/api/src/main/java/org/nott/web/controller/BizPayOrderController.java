package org.nott.web.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.common.annotation.RedisCache;
import org.nott.common.redis.RedisUtils;
import org.nott.common.utils.HutuUtils;
import org.nott.dto.MyOrderQueryDTO;
import org.nott.dto.UserSettleOrderDTO;
import org.nott.enums.OrderStatusEnum;
import org.nott.model.BizPayOrder;
import org.nott.service.delayed.handler.UserPayOrderQueueHandler;
import org.nott.service.service.IBizPayOrderService;
import org.nott.vo.FrontOrderVo;
import org.nott.vo.MyPayOrderVo;
import org.nott.vo.PayOrderVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

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
    @Resource
    private RedisUtils redisUtils;

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

    @ApiOperation("模拟支付成功")
    @GetMapping("simulateNotify/{orderId}")
    public ResponseEntity<?> simulateNotify(@PathVariable("orderId") Long orderId){
        bizPayOrderService.simulateNotify(orderId);
        return ResponseEntity.success();
    }

    @ApiOperation("查询订单状态")
    @GetMapping("orderQuery/{orderId}")
    public ResponseEntity<?> orderQuery(@PathVariable("orderId") Long orderId){
        PayOrderVo payOrder = bizPayOrderService.orderQuery(orderId);
        return ResponseEntity.successData(payOrder);
    }

    @ApiOperation("查询先前的订单排队情况")
    @GetMapping("orderFront/{orderId}")
    public ResponseEntity<?> orderFront(@PathVariable("orderId") Long orderId){
        FrontOrderVo frontOrderVo = bizPayOrderService.orderFront(orderId);
        return ResponseEntity.successData(frontOrderVo);
    }

    @ApiOperation("我的订单")
    @PostMapping("myOrder/{page}/{size}")
    public ResponseEntity<?> myOrder(@RequestBody MyOrderQueryDTO dto, @PathVariable("page") Integer page, @PathVariable("size") Integer size){
        Page<MyPayOrderVo> payOrderVoPage = bizPayOrderService.queryMyOrder(dto,page,size);
        return ResponseEntity.successData(payOrderVoPage);
    }

}
