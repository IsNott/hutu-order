package org.nott.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.service.api.IBizCommentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Nott
 * @date 2024-6-13
 */

@Api(tags = "商品评论管理")
@RestController
@RequestMapping("/sys/bizComment/")
public class SysBizCommentController {

    @Resource
    private IBizCommentService bizCommentService;

    @ApiOperation("评论数量统计")
    @GetMapping("count")
    public ResponseEntity<?> count(){
        return ResponseEntity.successData(bizCommentService.count());
    }
}
