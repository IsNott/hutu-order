package org.nott.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.common.utils.HutuUtils;
import org.nott.request.SysItemSkuSpecRequest;
import org.nott.vo.SysItemSkuSpecVo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import org.nott.model.SysItemSkuSpec;
import org.nott.service.admin.SysItemSkuSpecService;
import javax.annotation.Resource;
/**
* 商品SKU规格表前端控制器
*
* @author nott
* @version 1.0
* @description auto generated
*/
@Api(value = "商品SKU规格表管理接口", tags = "商品SKU规格表管理")
@RestController
@RequestMapping("/sysItemSkuSpec")
    public class SysItemSkuSpecController {

    @Resource
    private SysItemSkuSpecService service;

    @ApiOperation("分页查询")
    @PostMapping("/page/{page}/{size}")
    public ResponseEntity<IPage<SysItemSkuSpecVo>> page(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @RequestBody SysItemSkuSpecRequest req) {
        IPage<SysItemSkuSpecVo> result = service.queryPage(page, size, req.toDTO());
        return ResponseEntity.successData(result);
    }

    @ApiOperation("新增")
    @PostMapping("/add")
    public ResponseEntity<SysItemSkuSpecVo> add(@RequestBody SysItemSkuSpecRequest req) {
        SysItemSkuSpecVo vo = service.save(req.toDTO());
        return ResponseEntity.successData(vo);
    }

    @ApiOperation("详情")
    @GetMapping("/details/{id}")
    public ResponseEntity<SysItemSkuSpecVo> details(@PathVariable("id") Long id) {
        SysItemSkuSpecVo vo = HutuUtils.transToObject(service.getById(id), SysItemSkuSpecVo.class);
        return ResponseEntity.successData(vo);
    }

    @ApiOperation("更新")
    @PutMapping("/update/{id}")
    public ResponseEntity<SysItemSkuSpecVo> details(@PathVariable("id") Long id, @RequestBody SysItemSkuSpecRequest req) {
        SysItemSkuSpecVo vo = service.update(req.toDTO());
        return ResponseEntity.successData(vo);
    }

    @ApiOperation("删除")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.removeById(id);
        return ResponseEntity.success();
    }

}