package org.nott.web.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.dto.MyOrderQueryDTO;
import org.nott.dto.UserSettleOrderDTO;
import org.nott.service.api.BizPayOrderService;
import org.nott.vo.FrontOrderVo;
import org.nott.vo.MyPayOrderVo;
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
    private BizPayOrderService bizPayOrderService;

    @ApiOperation("用户下单")
    @PostMapping("settle")
    public ResponseEntity<?> settle(@RequestBody UserSettleOrderDTO userSettleOrderDTO){
        return ResponseEntity.successData(bizPayOrderService.doUserSettle(userSettleOrderDTO));
    }

    @ApiOperation("查询订单")
    @GetMapping("queryById/{id}")
//    @RedisCache(key = "queryById",item = "#id",expire = 180)
    public ResponseEntity<?> queryById(@PathVariable("id") Long id){
        PayOrderVo vo = bizPayOrderService.queryPayOrderById(id);
        return ResponseEntity.successData(vo);
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

    @ApiOperation("删除订单-用户")
    @PutMapping("deleteOrder/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable("orderId") Long orderId){
        bizPayOrderService.deleteOrder(orderId);
        return ResponseEntity.success();
    }

    @ApiOperation("取消订单-用户")
    @PutMapping("cancelOrder/{orderId}")
    public ResponseEntity<?> cancelOrder(@PathVariable("orderId") Long orderId){
        bizPayOrderService.cancelOrder(orderId);
        return ResponseEntity.success();
    }

}
