package org.nott.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.common.utils.HutuUtils;
import org.nott.request.SysUserRoleRequest;
import org.nott.vo.SysUserRoleVo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import org.nott.model.SysUserRole;
import org.nott.service.admin.SysUserRoleService;
import javax.annotation.Resource;
import java.util.List;

/**
* 系统用户-角色关系表前端控制器
*
* @author nott
* @version 1.0
* @description auto generated
*/
@Api(value = "系统用户-角色关系表管理接口", tags = "系统用户-角色关系表管理")
@RestController
@RequestMapping("/sysUserRole")
    public class SysUserRoleController {

    @Resource
    private SysUserRoleService service;

    @ApiOperation("分页查询")
    @PostMapping("/page/{page}/{size}")
    public ResponseEntity<IPage<SysUserRoleVo>> page(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @RequestBody SysUserRoleRequest req) {
        IPage<SysUserRoleVo> result = service.queryPage(page, size, req.toDTO());
        return ResponseEntity.successData(result);
    }

    @ApiOperation("新增")
    @PostMapping("/add")
    public ResponseEntity<SysUserRoleVo> add(@RequestBody SysUserRoleRequest req) {
        SysUserRoleVo vo = service.save(req.toDTO());
        return ResponseEntity.successData(vo);
    }

    @ApiOperation("设置用户")
    @PostMapping("/setRoleUsers/{roleId}")
    public ResponseEntity<Void> setRoleUsers(@PathVariable("roleId") Long roleId, @RequestBody List<Long> userIds) {
        service.setRoleUsers(roleId, userIds);
        return ResponseEntity.success();
    }

    @ApiOperation("详情")
    @GetMapping("/details/{id}")
    public ResponseEntity<SysUserRoleVo> details(@PathVariable("id") Long id) {
        SysUserRoleVo vo = HutuUtils.transToObject(service.getById(id), SysUserRoleVo.class);
        return ResponseEntity.successData(vo);
    }

    @ApiOperation("更新")
    @PutMapping("/update/{id}")
    public ResponseEntity<SysUserRoleVo> update(@PathVariable("id") Long id, @RequestBody SysUserRoleRequest req) {
        SysUserRoleVo vo = service.update(req.toDTO());
        return ResponseEntity.successData(vo);
    }

    @ApiOperation("删除")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.removeById(id);
        return ResponseEntity.success();
    }

}