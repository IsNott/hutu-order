package org.nott.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.common.utils.HutuUtils;
import org.nott.request.SysDataDictRequest;
import org.nott.vo.SysDataDictVo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import org.nott.model.SysDataDict;
import org.nott.service.admin.SysDataDictService;
import javax.annotation.Resource;
/**
* 数据字典前端控制器
*
* @author nott
* @version 1.0
* @description auto generated
*/
@Api(value = "数据字典管理接口", tags = "数据字典管理")
@RestController
@RequestMapping("/sysDataDict")
    public class SysDataDictController {

    @Resource
    private SysDataDictService service;

    @ApiOperation("分页查询")
    @PostMapping("/page/{page}/{size}")
    public ResponseEntity<IPage<SysDataDictVo>> page(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @RequestBody SysDataDictRequest req) {
        IPage<SysDataDictVo> result = service.queryPage(page, size, req.toDTO());
        return ResponseEntity.successData(result);
    }

    @ApiOperation("新增")
    @PostMapping("/add")
    public ResponseEntity<SysDataDictVo> add(@RequestBody SysDataDictRequest req) {
        SysDataDictVo vo = service.save(req.toDTO());
        return ResponseEntity.successData(vo);
    }

    @ApiOperation("详情")
    @GetMapping("/details/{id}")
    public ResponseEntity<SysDataDictVo> details(@PathVariable("id") Long id) {
        SysDataDictVo vo = HutuUtils.transToObject(service.getById(id), SysDataDictVo.class);
        return ResponseEntity.successData(vo);
    }

    @ApiOperation("更新")
    @PutMapping("/update/{id}")
    public ResponseEntity<SysDataDictVo> update(@PathVariable("id") Long id, @RequestBody SysDataDictRequest req) {
        SysDataDictVo vo = service.update(req.toDTO());
        return ResponseEntity.successData(vo);
    }

    @ApiOperation("删除")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.removeById(id);
        return ResponseEntity.success();
    }

}