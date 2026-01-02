package org.nott.web.controller;


import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.request.BizShopInfoRequest;
import org.nott.service.api.BizShopInfoService;
import org.nott.vo.ShopInfoVo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import javax.annotation.Resource;
import java.util.List;

/**
* 门店信息表前端控制器
*
* @author nott
* @version 1.0
* @description auto generated
*/
@Api(value = "门店信息表接口", tags = "门店信息表")
@RestController
@RequestMapping("/access/bizShopInfo")
    public class BizShopInfoController {

    @Resource
    private BizShopInfoService service;

    @ApiOperation("查询当前营业门店列表")
    @PostMapping("query")
    public ResponseEntity<?> queryBusinessShops(@RequestBody BizShopInfoRequest request){
        List<ShopInfoVo> shopInfos = service.businessShops(request);
        return ResponseEntity.successData(shopInfos);
    }

}