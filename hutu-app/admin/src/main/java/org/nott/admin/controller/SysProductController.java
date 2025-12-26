package org.nott.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.request.SysProductRequest;
import org.nott.vo.SysProductVo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import org.nott.service.admin.SysProductService;
import javax.annotation.Resource;

/**
* 商品表前端控制器
*
* @author nott
* @version 1.0
* @description auto generated
*/
@Api(value = "商品表管理接口", tags = "商品表管理")
@RestController
@RequestMapping("/sysProduct")
    public class SysProductController {

    @Resource
    private SysProductService service;

    @ApiOperation("分页查询")
    @PostMapping("/page/{page}/{size}")
    public ResponseEntity<IPage<SysProductVo>> page(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @RequestBody SysProductRequest req) {
        IPage<SysProductVo> result = service.queryPage(page, size, req.toDTO());
        return ResponseEntity.successData(result);
    }

    @ApiOperation("新增")
    @PostMapping("/add")
    public ResponseEntity<SysProductVo> add(@RequestBody SysProductRequest req) {
        SysProductVo vo = service.save(req.toDTO());
        return ResponseEntity.successData(vo);
    }

    @ApiOperation("详情")
    @GetMapping("/details/{id}")
    public ResponseEntity<SysProductVo> details(@PathVariable("id") Long id) {
        return ResponseEntity.successData(service.details(id));
    }

    @ApiOperation("更新")
    @PutMapping("/update/{id}")
    public ResponseEntity<SysProductVo> details(@PathVariable("id") Long id, @RequestBody SysProductRequest req) {
        SysProductVo vo = service.update(req.toDTO());
        return ResponseEntity.successData(vo);
    }

    @ApiOperation("删除")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.removeById(id);
        return ResponseEntity.success();
    }

}