package org.nott.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.dto.SysShopPageDTO;
import org.nott.service.api.IBizShopInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "门店信息管理接口")
@RestController
@RequestMapping("/sys/bizShopInfo")
public class SysBizShopInfoController {

    @Resource
    private IBizShopInfoService bizShopInfoService;

    @ApiOperation("门店列表分页查询")
    @PostMapping("/page/{page}/{size}")
    public ResponseEntity<?> page(@RequestBody SysShopPageDTO dto, @PathVariable("page") int page, @PathVariable("size") int size) {
        return ResponseEntity.successData(bizShopInfoService.page(dto, page, size));
    }
}
