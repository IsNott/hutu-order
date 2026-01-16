package org.nott.web.controller;

import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.vo.BizMenuCatalogVo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import org.nott.service.api.BizMenuCatalogService;
import javax.annotation.Resource;
import java.util.List;

/**
* 门店菜单分类表前端控制器
*
* @author nott
* @version 1.0
* @description auto generated
*/
@Api(value = "门店菜单分类表接口", tags = "门店菜单分类表")

@RestController
@RequestMapping("/access/bizMenuCatalog")
    public class BizMenuCatalogController {

    @Resource
    private BizMenuCatalogService service;

    @ApiOperation("获取门店菜单分类")
    @GetMapping("/listByShop/{shopId}")
    public ResponseEntity<List<BizMenuCatalogVo>> listByShopId(@PathVariable("shopId") Long shopId) {
        List<BizMenuCatalogVo> menus = service.listByShopId(shopId);
        return ResponseEntity.successData(menus);
    }
}