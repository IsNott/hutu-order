package org.nott.web.controller;

import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.vo.BizMenuVo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import org.nott.service.api.BizMenuService;
import javax.annotation.Resource;
import java.util.List;

/**
* 门店菜单表前端控制器
*
* @author nott
* @version 1.0
* @description auto generated
*/
@Api(value = "门店菜单表接口", tags = "门店菜单表")

@RestController
@RequestMapping("/access/bizMenu")
    public class BizMenuController {

    @Resource
    private BizMenuService service;

    @ApiOperation("获取门店菜单")
    @GetMapping("/listByShopId/{shopId}")
    public ResponseEntity<List<BizMenuVo>> listByShopId(@PathVariable("shopId") Long shopId) {
        List<BizMenuVo> menus = service.listByShopId(shopId);
        return ResponseEntity.successData(menus);
    }
}