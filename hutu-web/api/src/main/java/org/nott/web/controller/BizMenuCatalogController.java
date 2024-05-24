package org.nott.web.controller;

import org.nott.common.ResponseEntity;
import org.nott.web.service.IBizMenuCatalogService;
import org.nott.web.service.IBizMenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

}
