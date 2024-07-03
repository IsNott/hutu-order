package org.nott.web.controller;

import org.nott.common.ResponseEntity;
import org.nott.service.service.IBizMenuCatalogService;
import org.nott.service.service.IBizMenuService;
import org.nott.vo.MenuCatalogVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜单分类控制层
 * @author nott
 * @since 2024-05-24
 */
@RestController
@RequestMapping("/bizMenuCatalog")
public class BizMenuCatalogController {

    @Resource
    private IBizMenuCatalogService bizMenuCatalogService;

    @GetMapping("/list")
    public ResponseEntity<?> menuList(){
        return ResponseEntity.successData(bizMenuCatalogService.list());
    }

    @GetMapping("listByShop/{shopId}")
    public ResponseEntity<?> listByShop(@PathVariable Long shopId){
        List<MenuCatalogVo> vos = bizMenuCatalogService.getCatalogByShopId(shopId);
        return ResponseEntity.successData(vos);
    }

}
