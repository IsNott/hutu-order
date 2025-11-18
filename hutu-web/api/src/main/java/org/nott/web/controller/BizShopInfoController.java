package org.nott.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.service.api.BizShopInfoService;
import org.nott.vo.ShopInfoVo;
import org.springframework.web.bind.annotation.*;

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
@Api(tags = "门店信息")
@RestController
@RequestMapping("/bizShopInfo")
public class BizShopInfoController {

    @Resource
    private BizShopInfoService bizShopInfoService;

    @ApiOperation("查询当前营业门店列表")
    @GetMapping("list")
    public ResponseEntity<?> shopList(){
        List<ShopInfoVo> shopInfos = bizShopInfoService.listShopInfo();
        return ResponseEntity.successData(shopInfos);
    }

    @ApiOperation("查询默认门店")
    @GetMapping("default")
    public ResponseEntity<?> defaultShop(){
        ShopInfoVo vo = bizShopInfoService.getDefaultShop();
        return ResponseEntity.successData(vo);
    }

    @ApiOperation("搜索门店")
    @GetMapping("search")
    public ResponseEntity<?> search(String keyWord){
        List<ShopInfoVo> vos = bizShopInfoService.searchShopByKeyWord(keyWord);
        return ResponseEntity.successData(vos);
    }

    @ApiOperation("根据id查询门店")
    @GetMapping("getById/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        ShopInfoVo vo = bizShopInfoService.getShopById(id);
        return ResponseEntity.successData(vo);
    }

}
