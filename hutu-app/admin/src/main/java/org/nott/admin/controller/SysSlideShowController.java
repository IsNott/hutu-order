package org.nott.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.common.utils.HutuUtils;
import org.nott.request.SysSlideShowRequest;
import org.nott.vo.SysSlideShowVo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import org.nott.model.SysSlideShow;
import org.nott.service.admin.SysSlideShowService;
import javax.annotation.Resource;
/**
* 前端控制器
*
* @author nott
* @version 1.0
* @description auto generated
*/
@Api(value = "管理接口", tags = "管理")
@RestController
@RequestMapping("/sysSlideShow")
    public class SysSlideShowController {

    @Resource
    private SysSlideShowService service;

    @ApiOperation("分页查询")
    @PostMapping("/page/{page}/{size}")
    public ResponseEntity<IPage<SysSlideShowVo>> page(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @RequestBody SysSlideShowRequest req) {
        IPage<SysSlideShowVo> result = service.queryPage(page, size, req.toDTO());
        return ResponseEntity.successData(result);
    }

    @ApiOperation("新增")
    @PostMapping("/add")
    public ResponseEntity<SysSlideShowVo> add(@RequestBody SysSlideShowRequest req) {
        SysSlideShowVo vo = service.save(req.toDTO());
        return ResponseEntity.successData(vo);
    }

    @ApiOperation("详情")
    @GetMapping("/details/{id}")
    public ResponseEntity<SysSlideShowVo> details(@PathVariable("id") Long id) {
        SysSlideShowVo vo = HutuUtils.transToObject(service.getById(id), SysSlideShowVo.class);
        return ResponseEntity.successData(vo);
    }

    @ApiOperation("更新")
    @PutMapping("/update/{id}")
    public ResponseEntity<SysSlideShowVo> update(@PathVariable("id") Long id, @RequestBody SysSlideShowRequest req) {
        SysSlideShowVo vo = service.update(req.toDTO());
        return ResponseEntity.successData(vo);
    }

    @ApiOperation("删除")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.removeById(id);
        return ResponseEntity.success();
    }

}