package org.nott.admin.controller;

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
@RestController
@RequestMapping("/sys/bizPayOrder")
public class SysBizPayOrderController {

    @Resource
    private IBizPayOrderService bizPayOrderService;

    @GetMapping("/count")
    public ResponseEntity<?> countOrder(){
        return ResponseEntity.successData(bizPayOrderService.count());
    }

    @GetMapping("/countPurchases")
    public ResponseEntity<?> countPurchases(){
        String count = bizPayOrderService.countPurchases();
        return ResponseEntity.successData(count);
    }



}
