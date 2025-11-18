package org.nott.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.service.api.BizCommonRemarkService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Nott
 * @date 2025-3-24
 */

@Api("常用备注")
@RestController
@RequestMapping("/bizCommonRemark")
public class BizCommonRemarkController {

    @Resource
    private BizCommonRemarkService bizCommonRemarkService;

    @GetMapping("query/{size}")
    @ApiOperation("查询常用备注")
    public ResponseEntity<?> queryCommonRemark(@PathVariable("size") int size) {
        return ResponseEntity.successData(bizCommonRemarkService.queryCommonRemark(size));
    }

}
