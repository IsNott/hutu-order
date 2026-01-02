package org.nott.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.common.utils.HutuUtils;
import org.nott.request.SysItemSkuOptionRequest;
import org.nott.vo.SysItemSkuOptionVo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import org.nott.model.SysItemSkuOption;
import org.nott.service.admin.SysItemSkuOptionService;
import javax.annotation.Resource;
/**
* SKU规格选项表前端控制器
*
* @author nott
* @version 1.0
* @description auto generated
*/
@Api(value = "SKU规格选项表管理接口", tags = "SKU规格选项表管理")
@RestController
@RequestMapping("/sysItemSkuOption")
    public class SysItemSkuOptionController {

    @Resource
    private SysItemSkuOptionService service;

    @ApiOperation("分页查询")
    @PostMapping("/page/{page}/{size}")
    public ResponseEntity<IPage<SysItemSkuOptionVo>> page(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @RequestBody SysItemSkuOptionRequest req) {
        IPage<SysItemSkuOptionVo> result = service.queryPage(page, size, req.toDTO());
        return ResponseEntity.successData(result);
    }

    @ApiOperation("新增")
    @PostMapping("/add")
    public ResponseEntity<SysItemSkuOptionVo> add(@RequestBody SysItemSkuOptionRequest req) {
        SysItemSkuOptionVo vo = service.save(req.toDTO());
        return ResponseEntity.successData(vo);
    }

    @ApiOperation("详情")
    @GetMapping("/details/{id}")
    public ResponseEntity<SysItemSkuOptionVo> details(@PathVariable("id") Long id) {
        SysItemSkuOptionVo vo = HutuUtils.transToObject(service.getById(id), SysItemSkuOptionVo.class);
        return ResponseEntity.successData(vo);
    }

    @ApiOperation("更新")
    @PutMapping("/update/{id}")
    public ResponseEntity<SysItemSkuOptionVo> details(@PathVariable("id") Long id, @RequestBody SysItemSkuOptionRequest req) {
        SysItemSkuOptionVo vo = service.update(req.toDTO());
        return ResponseEntity.successData(vo);
    }

    @ApiOperation("删除")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.removeById(id);
        return ResponseEntity.success();
    }

}