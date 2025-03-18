package org.nott.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.service.api.IBizUserPointService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 用户积分表 前端控制器
 * </p>
 *
 * @author nott
 * @since 2024-08-16
 */
@Api("用户积分")
@RestController
@RequestMapping("/bizUserPoint")
public class BizUserPointController {

    @Resource
    private IBizUserPointService bizUserPointService;

    @ApiOperation("用户查询可用积分")
    @PostMapping("/myPoint")
    public ResponseEntity<?> myPoint(){
        return ResponseEntity.successData(bizUserPointService.queryUsablePoint());
    }


}
