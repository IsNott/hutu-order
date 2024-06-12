package org.nott.web.controller;

import org.nott.common.ResponseEntity;
import org.nott.vo.MenuItemVo;
import org.nott.service.service.IBizMenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜单控制层
 * @author nott
 * @since 2024-05-24
 */
@RestController
@RequestMapping("/bizMenu")
public class BizMenuController {

    @Resource
    private IBizMenuService bizMenuService;

    @GetMapping("listByCatalogId/{catalogId}")
    public ResponseEntity<?> listByCatalogId(@PathVariable("catalogId") String catalogId){
        List<MenuItemVo> menuList = bizMenuService.getByCatalogId(catalogId);
        return ResponseEntity.successData(menuList);
    }

}
