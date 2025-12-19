package org.nott.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.common.utils.HutuUtils;
import org.nott.request.SysShopInfoRequest;
import org.nott.service.admin.SysShopInfoService;
import org.nott.vo.SysShopInfoVo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import org.nott.model.SysShopInfo;

import javax.annotation.Resource;
/**
* 门店信息表前端控制器
*
* @author nott
* @date 2025-12-01
* @version 1.0
* @description auto generated
*/
@Api(value = "门店信息表接口", tags = "门店信息表")
@RestController
@RequestMapping("/sysShopInfo")
    public class SysShopInfoController {

    @Resource
    private SysShopInfoService service;

    @ApiOperation("分页查询")
    @PostMapping("/page/{page}/{size}")
    public ResponseEntity<IPage<SysShopInfoVo>> page(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @RequestBody SysShopInfoRequest req) {
        IPage<SysShopInfoVo> result = service.queryPage(page, size, req.toDTO());
        return ResponseEntity.successData(result);
    }

    @ApiOperation("详情")
    @PostMapping("/details/{id}")
    public ResponseEntity<SysShopInfoVo> details(@PathVariable("id") Long id) {
        SysShopInfoVo vo = HutuUtils.toVO(service.getById(id), SysShopInfoVo.class);
        return ResponseEntity.successData(vo);
    }

    @ApiOperation("更新")
    @PutMapping("/update/{id}")
    public ResponseEntity<SysShopInfoVo> details(@PathVariable("id") Long id, @RequestBody SysShopInfo entity) {
        service.updateById(entity);
        return ResponseEntity.successData(HutuUtils.toVO(entity, SysShopInfoVo.class));
    }

    @ApiOperation("删除")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.removeById(id);
        return ResponseEntity.success();
    }

}