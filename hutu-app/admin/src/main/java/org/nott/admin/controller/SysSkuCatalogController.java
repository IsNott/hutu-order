package org.nott.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.common.utils.HutuUtils;
import org.nott.model.SysShopInfo;
import org.nott.request.SysShopInfoRequest;
import org.nott.request.SysSkuCatalogRequest;
import org.nott.vo.SysShopInfoVo;
import org.nott.vo.SysSkuCatalogVo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import org.nott.model.SysSkuCatalog;
import org.nott.service.admin.SysSkuCatalogService;
import javax.annotation.Resource;
/**
* sku分类表前端控制器
*
* @author nott
* @version 1.0
* @description auto generated
*/
@Api(value = "sku分类表管理接口", tags = "sku分类表管理")
@RestController
@RequestMapping("/sysSkuCatalog")
    public class SysSkuCatalogController {

    @Resource
    private SysSkuCatalogService service;

    @ApiOperation("分页查询")
    @PostMapping("/page/{page}/{size}")
    public ResponseEntity<IPage<SysSkuCatalogVo>> page(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @RequestBody SysSkuCatalogRequest req) {
        IPage<SysSkuCatalogVo> result = service.queryPage(page, size, req.toDTO());
        return ResponseEntity.successData(result);
    }

    @ApiOperation("新增")
    @PostMapping("/add")
    public ResponseEntity<SysSkuCatalogVo> add(@RequestBody SysSkuCatalogRequest req) {
        SysSkuCatalogVo vo = service.save(req.toDTO());
        return ResponseEntity.successData(vo);
    }

    @ApiOperation("详情")
    @GetMapping("/details/{id}")
    public ResponseEntity<SysSkuCatalogVo> details(@PathVariable("id") Long id) {
        SysSkuCatalogVo vo = HutuUtils.transToObject(service.getById(id), SysSkuCatalogVo.class);
        return ResponseEntity.successData(vo);
    }

    @ApiOperation("更新")
    @PutMapping("/update/{id}")
    public ResponseEntity<SysSkuCatalogVo> details(@PathVariable("id") Long id, @RequestBody SysSkuCatalogRequest req) {
        SysSkuCatalogVo vo = service.update(req.toDTO());
        return ResponseEntity.successData(vo);
    }

    @ApiOperation("删除")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.removeById(id);
        return ResponseEntity.success();
    }

}