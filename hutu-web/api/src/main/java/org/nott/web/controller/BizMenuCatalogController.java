package org.nott.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.common.annotation.RedisCache;
import org.nott.service.api.BizMenuCatalogService;
import org.nott.vo.MenuCatalogVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜单分类控制层
 *
 * @author nott
 * @since 2024-05-24
 */
@Api(tags = "菜单分类")
@RestController
@RequestMapping("/bizMenuCatalog")
public class BizMenuCatalogController {

    @Resource
    private BizMenuCatalogService bizMenuCatalogService;

    @Deprecated
    @ApiOperation(value = "分类列表")
    @GetMapping("/list")
    public ResponseEntity<?> menuList() {
        return ResponseEntity.successData(bizMenuCatalogService.list());
    }

    @GetMapping("listByShop/{shopId}")
    @RedisCache(item = "#shopId")
    @ApiOperation(value = "门店分类列表", notes = "根据门店id获取菜单分类")
    public ResponseEntity<?> listByShop(@PathVariable Long shopId) {
        List<MenuCatalogVo> vos = bizMenuCatalogService.getCatalogByShopId(shopId);
        return ResponseEntity.successData(vos);
    }

}
