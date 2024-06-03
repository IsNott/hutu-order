package org.nott.web.controller;

import org.nott.common.ResponseEntity;
import org.nott.vo.ItemSkuVo;
import org.nott.web.service.IBizItemSkuRelationService;
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
@RestController
@RequestMapping("/sku")
public class BizSkuController {

    @Resource
    private IBizItemSkuRelationService bizItemSkuRelationService;

    @GetMapping("{itemId}")
    public ResponseEntity<?> skuCatalogByItemId(@PathVariable Long itemId){
        List<ItemSkuVo> itemSkuVoList = bizItemSkuRelationService.selectItemSkuList(itemId);
        return ResponseEntity.successData(itemSkuVoList);
    }

}
