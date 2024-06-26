package org.nott.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.nott.common.ResponseEntity;
import org.nott.service.service.IBizPayWayService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/bizPayWay")
public class BizPayWayController {

    @Resource
    private IBizPayWayService bizPayWayService;

    @PostMapping("list")
    public ResponseEntity<?> listPayWay(){
        return ResponseEntity.successData(bizPayWayService.listPayWay());
    }
}
