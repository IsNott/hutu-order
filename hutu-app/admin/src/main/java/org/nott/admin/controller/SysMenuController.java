package org.nott.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.common.utils.HutuUtils;
import org.nott.request.SysMenuRequest;
import org.nott.vo.SysMenuTreeNodeVo;
import org.nott.vo.SysMenuVo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import org.nott.model.SysMenu;
import org.nott.service.admin.SysMenuService;
import javax.annotation.Resource;
import java.util.List;

/**
* 系统菜单表前端控制器
*
* @author nott
* @version 1.0
* @description auto generated
*/
@Api(value = "系统菜单表管理接口", tags = "系统菜单表管理")
@RestController
@RequestMapping("/sysMenu")
    public class SysMenuController {

    @Resource
    private SysMenuService service;

    @ApiOperation("分页查询")
    @PostMapping("/page/{page}/{size}")
    public ResponseEntity<IPage<SysMenuVo>> page(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @RequestBody SysMenuRequest req) {
        IPage<SysMenuVo> result = service.queryPage(page, size, req.toDTO());
        return ResponseEntity.successData(result);
    }

    @ApiOperation("分页树查询")
    @PostMapping("/treePage/{page}/{size}")
    public ResponseEntity<IPage<SysMenuTreeNodeVo>> treeNodePage(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @RequestBody SysMenuRequest req) {
        IPage<SysMenuTreeNodeVo> result = service.treeNodePage(page, size, req.toDTO());
        return ResponseEntity.successData(result);
    }

    @ApiOperation("新增")
    @PostMapping("/add")
    public ResponseEntity<SysMenuVo> add(@RequestBody SysMenuRequest req) {
        SysMenuVo vo = service.save(req.toDTO());
        return ResponseEntity.successData(vo);
    }

    @ApiOperation("详情")
    @GetMapping("/details/{id}")
    public ResponseEntity<SysMenuVo> details(@PathVariable("id") Long id) {
        SysMenuVo vo = HutuUtils.transToObject(service.getById(id), SysMenuVo.class);
        return ResponseEntity.successData(vo);
    }

    @ApiOperation("更新")
    @PutMapping("/update/{id}")
    public ResponseEntity<SysMenuVo> update(@PathVariable("id") Long id, @RequestBody SysMenuRequest req) {
        SysMenuVo vo = service.update(req.toDTO());
        return ResponseEntity.successData(vo);
    }

    @ApiOperation("删除")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.deleteNode(id);
        return ResponseEntity.success();
    }

    @ApiOperation("批量更新父级id")
    @PutMapping("/updateParent/{newParentId}")
    public ResponseEntity<Void> updateParent(@RequestBody List<Long> ids, @PathVariable("newParentId") Long newParentId) {
        service.updateParent(ids, newParentId);
        return ResponseEntity.success();
    }

    @ApiOperation("获取用户菜单树")
    @GetMapping("/menu")
    public ResponseEntity<List<SysMenuTreeNodeVo>> getUserMenuTree() {
        List<SysMenuTreeNodeVo> menuTree = service.getUserMenuTree();
        return ResponseEntity.successData(menuTree);
    }

}