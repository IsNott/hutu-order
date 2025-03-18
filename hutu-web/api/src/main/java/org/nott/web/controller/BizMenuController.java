package org.nott.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.vo.MenuItemVo;
import org.nott.service.api.IBizMenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜单控制层
 *
 * @author nott
 * @since 2024-05-24
 */
@Api(tags = "菜单")
@RestController
@RequestMapping("/bizMenu")
public class BizMenuController {

    @Resource
    private IBizMenuService bizMenuService;

    @Deprecated
    @ApiOperation("菜单列表")
    @GetMapping("listByCatalogId/{catalogId}")
    public ResponseEntity<?> listByCatalogId(@PathVariable("catalogId") String catalogId) {
        List<MenuItemVo> menuList = bizMenuService.getByCatalogId(catalogId);
        return ResponseEntity.successData(menuList);
    }

    @ApiOperation(value = "门店菜单列表",tags = "根据门店+分类id查询")
    @GetMapping("listByShopCatalogId/{shopId}/{catalogId}")
    public ResponseEntity<?> listByCatalogId(@PathVariable("shopId") Long shopId, @PathVariable("catalogId") String catalogId) {
        List<MenuItemVo> menuList = bizMenuService.getByShopCatalogId(shopId,catalogId);
        return ResponseEntity.successData(menuList);
    }

}
