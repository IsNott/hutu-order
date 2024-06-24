package org.nott.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.nott.common.ResponseEntity;
import org.nott.dto.SkuCatalogSearchDTO;
import org.nott.service.service.IBizSkuCatalogService;
import org.nott.vo.SkuCatalogItemVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author nott
 * @since 2024-06-07
 */
@RestController
@RequestMapping("/sys/bizSkuCatalog")
public class SysBizSkuCatalogController {

    @Resource
    private IBizSkuCatalogService bizSkuCatalogService;

    @PostMapping("queryBizItemRelByPage/{page}/{size}")
    public ResponseEntity<?> page(@RequestBody SkuCatalogSearchDTO skuCatalogSearchDTO, @PathVariable Integer page, @PathVariable Integer size) {
        Page<SkuCatalogItemVo> skuCatalogItemVoPage = bizSkuCatalogService.pageSkuCatalog(skuCatalogSearchDTO, page, size);
        return ResponseEntity.successData(skuCatalogItemVoPage);
    }

    @PostMapping("delete/{id}")
    public ResponseEntity<?> deleteSkuCatalog(@PathVariable Long id) {
        bizSkuCatalogService.deleteSkuCatalog(id);
        return ResponseEntity.success();
    }

}
