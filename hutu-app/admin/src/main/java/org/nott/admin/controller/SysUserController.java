package org.nott.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.common.utils.HutuUtils;
import org.nott.request.SysUserRequest;
import org.nott.vo.SysUserVo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import org.nott.model.SysUser;
import org.nott.service.admin.SysUserService;
import javax.annotation.Resource;
/**
* 系统用户表前端控制器
*
* @author nott
* @version 1.0
* @description auto generated
*/
@Api(value = "系统用户表管理接口", tags = "系统用户表管理")
@RestController
@RequestMapping("/sysUser")
    public class SysUserController {

    @Resource
    private SysUserService service;

    @ApiOperation("分页查询")
    @PostMapping("/page/{page}/{size}")
    public ResponseEntity<IPage<SysUserVo>> page(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @RequestBody SysUserRequest req) {
        IPage<SysUserVo> result = service.queryPage(page, size, req.toDTO());
        return ResponseEntity.successData(result);
    }

    @ApiOperation("新增")
    @PostMapping("/add")
    public ResponseEntity<SysUserVo> add(@RequestBody SysUserRequest req) {
        SysUserVo vo = service.save(req.toDTO());
        return ResponseEntity.successData(vo);
    }

    @ApiOperation("详情")
    @GetMapping("/details/{id}")
    public ResponseEntity<SysUserVo> details(@PathVariable("id") Long id) {
        SysUserVo vo = HutuUtils.transToObject(service.getById(id), SysUserVo.class);
        return ResponseEntity.successData(vo);
    }

    @ApiOperation("更新")
    @PutMapping("/update/{id}")
    public ResponseEntity<SysUserVo> update(@PathVariable("id") Long id, @RequestBody SysUserRequest req) {
        SysUserVo vo = service.update(req.toDTO());
        return ResponseEntity.successData(vo);
    }

    @ApiOperation("删除")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.removeById(id);
        return ResponseEntity.success();
    }

    @ApiOperation("重置密码")
    @PutMapping("/resetPassword/{id}")
    public ResponseEntity<Void> resetPassword(@PathVariable("id") Long id) {
        String msg = service.resetPassword(id);
        return ResponseEntity.success(msg);
    }

}