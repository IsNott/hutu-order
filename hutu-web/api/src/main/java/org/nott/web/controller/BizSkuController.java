package org.nott.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.vo.ItemSkuVo;
import org.nott.service.service.IBizItemSkuRelationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Nott
 * @date 2024-6-3
 */
@Api(tags = "SKU信息")
@RestController
@RequestMapping("/sku")
public class BizSkuController {

    @Resource
    private IBizItemSkuRelationService bizItemSkuRelationService;

    @ApiOperation("根据商品id查询sku列表")
    @GetMapping("{itemId}")
    public ResponseEntity<?> skuCatalogByItemId(@PathVariable Long itemId){
        List<ItemSkuVo> itemSkuVoList = bizItemSkuRelationService.selectItemSkuList(itemId);
        return ResponseEntity.successData(itemSkuVoList);
    }

}
