package org.nott.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.service.api.BizUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Nott
 * @date 2024-6-12
 */

@Api(tags = "用户管理")
@RestController
@RequestMapping("/sys/bizUser/")
public class SysBizUserController {

    @Resource
    private BizUserService bizUserService;

    @ApiOperation("数量统计")
    @GetMapping("/count")
    public ResponseEntity<?> countBizUser(){
        return ResponseEntity.successData(bizUserService.count());
    }
}
