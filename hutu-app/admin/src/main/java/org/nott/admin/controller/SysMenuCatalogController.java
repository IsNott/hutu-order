package org.nott.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.common.utils.HutuUtils;
import org.nott.request.SysMenuCatalogRequest;
import org.nott.vo.PayOrderVo;
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
    public ResponseEntity<IPage<PayOrderVo.SysMenuCatalogVo>> page(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @RequestBody SysMenuCatalogRequest req) {
        IPage<PayOrderVo.SysMenuCatalogVo> result = service.queryPage(page, size, req.toDTO());
        return ResponseEntity.successData(result);
    }

    @ApiOperation("新增")
    @PostMapping("/add")
    public ResponseEntity<PayOrderVo.SysMenuCatalogVo> add(@RequestBody SysMenuCatalogRequest req) {
        PayOrderVo.SysMenuCatalogVo vo = service.save(req.toDTO());
        return ResponseEntity.successData(vo);
    }

    @ApiOperation("详情")
    @GetMapping("/details/{id}")
    public ResponseEntity<PayOrderVo.SysMenuCatalogVo> details(@PathVariable("id") Long id) {
        PayOrderVo.SysMenuCatalogVo vo = HutuUtils.transToObject(service.getById(id), PayOrderVo.SysMenuCatalogVo.class);
        return ResponseEntity.successData(vo);
    }

    @ApiOperation("更新")
    @PutMapping("/update/{id}")
    public ResponseEntity<PayOrderVo.SysMenuCatalogVo> details(@PathVariable("id") Long id, @RequestBody SysMenuCatalogRequest req) {
        PayOrderVo.SysMenuCatalogVo vo = service.update(req.toDTO());
        return ResponseEntity.successData(vo);
    }

    @ApiOperation("删除")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.removeById(id);
        return ResponseEntity.success();
    }

}