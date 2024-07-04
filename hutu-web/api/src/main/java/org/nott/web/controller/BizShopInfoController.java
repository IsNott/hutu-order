package org.nott.web.controller;

import org.nott.common.ResponseEntity;
import org.nott.service.service.IBizShopInfoService;
import org.nott.vo.ShopInfoVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author nott
 * @since 2024-06-26
 */
@RestController
@RequestMapping("/bizShopInfo")
public class BizShopInfoController {

    @Resource
    private IBizShopInfoService bizShopInfoService;

    @GetMapping("list")
    public ResponseEntity<?> shopList(){
        List<ShopInfoVo> shopInfos = bizShopInfoService.listShopInfo();
        return ResponseEntity.successData(shopInfos);
    }

    @GetMapping("default")
    public ResponseEntity<?> defaultShop(){
        ShopInfoVo vo = bizShopInfoService.getDefaultShop();
        return ResponseEntity.successData(vo);
    }

}
