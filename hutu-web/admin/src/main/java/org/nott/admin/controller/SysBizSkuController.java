package org.nott.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.dto.SkuCatalogSearchDTO;
import org.nott.service.api.IBizSkuCatalogService;
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
@Api(tags = "sku管理")
@RestController
@RequestMapping("/sys/sku")
public class SysBizSkuController {

    @Resource
    private IBizSkuCatalogService bizSkuCatalogService;

    @ApiOperation("分页查询分类下的sku item")
    @PostMapping("queryBizItemRelByPage/{page}/{size}")
    public ResponseEntity<?> page(@RequestBody SkuCatalogSearchDTO skuCatalogSearchDTO, @PathVariable Integer page, @PathVariable Integer size) {
        Page<SkuCatalogItemVo> skuCatalogItemVoPage = bizSkuCatalogService.pageSkuCatalog(skuCatalogSearchDTO, page, size);
        return ResponseEntity.successData(skuCatalogItemVoPage);
    }

    @ApiOperation("删除sku分类")
    @PostMapping("delete/{id}")
    public ResponseEntity<?> deleteSkuCatalog(@PathVariable Long id) {
        bizSkuCatalogService.deleteSkuCatalog(id);
        return ResponseEntity.success();
    }

}
