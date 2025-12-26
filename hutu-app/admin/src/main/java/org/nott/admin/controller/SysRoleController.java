package org.nott.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.common.utils.HutuUtils;
import org.nott.request.SysRoleRequest;
import org.nott.vo.SysRoleVo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import org.nott.model.SysRole;
import org.nott.service.admin.SysRoleService;
import javax.annotation.Resource;
/**
* 系统角色表前端控制器
*
* @author nott
* @version 1.0
* @description auto generated
*/
@Api(value = "系统角色表管理接口", tags = "系统角色表管理")
@RestController
@RequestMapping("/sysRole")
    public class SysRoleController {

    @Resource
    private SysRoleService service;

    @ApiOperation("分页查询")
    @PostMapping("/page/{page}/{size}")
    public ResponseEntity<IPage<SysRoleVo>> page(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @RequestBody SysRoleRequest req) {
        IPage<SysRoleVo> result = service.queryPage(page, size, req.toDTO());
        return ResponseEntity.successData(result);
    }

    @ApiOperation("新增")
    @PostMapping("/add")
    public ResponseEntity<SysRoleVo> add(@RequestBody SysRoleRequest req) {
        SysRoleVo vo = service.save(req.toDTO());
        return ResponseEntity.successData(vo);
    }

    @ApiOperation("详情")
    @GetMapping("/details/{id}")
    public ResponseEntity<SysRoleVo> details(@PathVariable("id") Long id) {
        SysRoleVo vo = HutuUtils.transToObject(service.getById(id), SysRoleVo.class);
        return ResponseEntity.successData(vo);
    }

    @ApiOperation("更新")
    @PutMapping("/update/{id}")
    public ResponseEntity<SysRoleVo> details(@PathVariable("id") Long id, @RequestBody SysRoleRequest req) {
        SysRoleVo vo = service.update(req.toDTO());
        return ResponseEntity.successData(vo);
    }

    @ApiOperation("删除")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.removeById(id);
        return ResponseEntity.success();
    }

}