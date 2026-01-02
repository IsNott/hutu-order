package org.nott.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.common.utils.HutuUtils;
import org.nott.request.SysSlideShowItemRequest;
import org.nott.vo.SysSlideShowItemVo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import org.nott.model.SysSlideShowItem;
import org.nott.service.admin.SysSlideShowItemService;
import javax.annotation.Resource;
/**
* 轮播图关联内容前端控制器
*
* @author nott
* @version 1.0
* @description auto generated
*/
@Api(value = "轮播图关联内容管理接口", tags = "轮播图关联内容管理")
@RestController
@RequestMapping("/sysSlideShowItem")
    public class SysSlideShowItemController {

    @Resource
    private SysSlideShowItemService service;

    @ApiOperation("分页查询")
    @PostMapping("/page/{page}/{size}")
    public ResponseEntity<IPage<SysSlideShowItemVo>> page(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @RequestBody SysSlideShowItemRequest req) {
        IPage<SysSlideShowItemVo> result = service.queryPage(page, size, req.toDTO());
        return ResponseEntity.successData(result);
    }

    @ApiOperation("新增")
    @PostMapping("/add")
    public ResponseEntity<SysSlideShowItemVo> add(@RequestBody SysSlideShowItemRequest req) {
        SysSlideShowItemVo vo = service.save(req.toDTO());
        return ResponseEntity.successData(vo);
    }

    @ApiOperation("详情")
    @GetMapping("/details/{id}")
    public ResponseEntity<SysSlideShowItemVo> details(@PathVariable("id") Long id) {
        SysSlideShowItemVo vo = HutuUtils.transToObject(service.getById(id), SysSlideShowItemVo.class);
        return ResponseEntity.successData(vo);
    }

    @ApiOperation("更新")
    @PutMapping("/update/{id}")
    public ResponseEntity<SysSlideShowItemVo> update(@PathVariable("id") Long id, @RequestBody SysSlideShowItemRequest req) {
        SysSlideShowItemVo vo = service.update(req.toDTO());
        return ResponseEntity.successData(vo);
    }

    @ApiOperation("删除")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.removeById(id);
        return ResponseEntity.success();
    }

}