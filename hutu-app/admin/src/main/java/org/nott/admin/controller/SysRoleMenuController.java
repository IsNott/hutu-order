package org.nott.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.common.utils.HutuUtils;
import org.nott.request.SysRoleMenuRequest;
import org.nott.vo.SysRoleMenuVo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import org.nott.model.SysRoleMenu;
import org.nott.service.admin.SysRoleMenuService;
import javax.annotation.Resource;
import java.util.List;

/**
* 角色-菜单权限表前端控制器
*
* @author nott
* @version 1.0
* @description auto generated
*/
@Api(value = "角色-菜单权限表管理接口", tags = "角色-菜单权限表管理")
@RestController
@RequestMapping("/sysRoleMenu")
    public class SysRoleMenuController {

    @Resource
    private SysRoleMenuService service;

    @ApiOperation("分页查询")
    @PostMapping("/page/{page}/{size}")
    public ResponseEntity<IPage<SysRoleMenuVo>> page(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @RequestBody SysRoleMenuRequest req) {
        IPage<SysRoleMenuVo> result = service.queryPage(page, size, req.toDTO());
        return ResponseEntity.successData(result);
    }

    @ApiOperation("根据角色id查找权限列表")
    @GetMapping("/queryByRoleId/{roleId}")
    public ResponseEntity<List<SysRoleMenuVo>> queryByRoleId(@PathVariable("roleId") Long roleId) {
        return ResponseEntity.successData(service.getListByRoleId(roleId));
    }

    @ApiOperation("新增")
    @PostMapping("/add")
    public ResponseEntity<SysRoleMenuVo> add(@RequestBody SysRoleMenuRequest req) {
        SysRoleMenuVo vo = service.save(req.toDTO());
        return ResponseEntity.successData(vo);
    }

    @ApiOperation("设置权限")
    @PostMapping("/setRoleMenus/{roleId}")
    public ResponseEntity<Void> setRoleMenus(@PathVariable("roleId") Long roleId, @RequestBody List<Long> menuIds) {
        service.setRoleMenus(roleId, menuIds);
        return ResponseEntity.success();
    }

    @ApiOperation("详情")
    @GetMapping("/details/{id}")
    public ResponseEntity<SysRoleMenuVo> details(@PathVariable("id") Long id) {
        SysRoleMenuVo vo = HutuUtils.transToObject(service.getById(id), SysRoleMenuVo.class);
        return ResponseEntity.successData(vo);
    }

    @ApiOperation("更新")
    @PutMapping("/update/{id}")
    public ResponseEntity<SysRoleMenuVo> update(@PathVariable("id") Long id, @RequestBody SysRoleMenuRequest req) {
        SysRoleMenuVo vo = service.update(req.toDTO());
        return ResponseEntity.successData(vo);
    }

    @ApiOperation("删除")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.removeById(id);
        return ResponseEntity.success();
    }

}