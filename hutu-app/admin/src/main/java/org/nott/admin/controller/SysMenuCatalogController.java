package org.nott.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.common.utils.HutuUtils;
import org.nott.request.SysMenuCatalogCopyRequest;
import org.nott.request.SysMenuCatalogRequest;
import org.nott.vo.SysMenuCatalogVo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import org.nott.service.admin.SysMenuCatalogService;
import javax.annotation.Resource;
/**
* 门店菜单分类表前端控制器
*
* @author nott
* @version 1.0
* @description auto generated
*/
@Api(value = "门店菜单分类表管理接口", tags = "门店菜单分类表管理")
@RestController
@RequestMapping("/sysMenuCatalog")
    public class SysMenuCatalogController {

    @Resource
    private SysMenuCatalogService service;

    @ApiOperation("分页查询")
    @PostMapping("/page/{page}/{size}")
    public ResponseEntity<IPage<SysMenuCatalogVo>> page(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @RequestBody SysMenuCatalogRequest req) {
        IPage<SysMenuCatalogVo> result = service.queryPage(page, size, req.toDTO());
        return ResponseEntity.successData(result);
    }

    @ApiOperation("新增")
    @PostMapping("/add")
    public ResponseEntity<SysMenuCatalogVo> add(@RequestBody SysMenuCatalogRequest req) {
        SysMenuCatalogVo vo = service.save(req.toDTO());
        return ResponseEntity.successData(vo);
    }

    @ApiOperation("详情")
    @GetMapping("/details/{id}")
    public ResponseEntity<SysMenuCatalogVo> details(@PathVariable("id") Long id) {
        SysMenuCatalogVo vo = HutuUtils.transToObject(service.getById(id), SysMenuCatalogVo.class);
        return ResponseEntity.successData(vo);
    }

    @ApiOperation("更新")
    @PutMapping("/update/{id}")
    public ResponseEntity<SysMenuCatalogVo> details(@PathVariable("id") Long id, @RequestBody SysMenuCatalogRequest req) {
        SysMenuCatalogVo vo = service.update(req.toDTO());
        return ResponseEntity.successData(vo);
    }

    @ApiOperation("删除")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.removeById(id);
        return ResponseEntity.success();
    }

    @ApiOperation("复制分类到其他门店")
    @PostMapping("/copy2Shops")
    public ResponseEntity<Void> delete(@RequestBody SysMenuCatalogCopyRequest req) {
        service.copy2Shops(req.toDTO());
        return ResponseEntity.success();
    }

}