package org.nott.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.service.service.IBizPayOrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author nott
 * @since 2024-06-07
 */

@Api(tags = "订单管理")
@RestController
@RequestMapping("/sys/bizPayOrder")
public class SysBizPayOrderController {

    @Resource
    private IBizPayOrderService bizPayOrderService;

    @ApiOperation("数量统计")
    @GetMapping("/count")
    public ResponseEntity<?> countOrder(){
        return ResponseEntity.successData(bizPayOrderService.count());
    }

    @ApiOperation("营业额统计")
    @GetMapping("/countPurchases")
    public ResponseEntity<?> countPurchases(){
        String count = bizPayOrderService.countPurchases();
        return ResponseEntity.successData(count);
    }



}
