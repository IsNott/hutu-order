package org.nott.web.controller;

import cn.dev33.satoken.stp.StpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.dto.UserPackageAddDTO;
import org.nott.dto.UserPackageQueryDTO;
import org.nott.vo.UserPackageVo;
import org.nott.service.service.IBizItemService;
import org.nott.service.service.IBizUserPackageService;
import org.nott.service.service.IBizUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author nott
 * @since 2024-06-03
 */
@Api(tags = "用户购物袋")
@RestController
@RequestMapping("/bizUserPackage")
public class BizUserPackageController {

    @Resource
    private IBizUserPackageService bizUserPackageService;

    @Resource
    private IBizItemService bizItemService;

    @PostMapping("/query")
    @ApiOperation("查询当前登录用户购物袋")
    public ResponseEntity<?> queryPackage(){
        List<UserPackageVo> userPackageVos = bizUserPackageService.queryPackageInfoByUserId();
        return ResponseEntity.successData(userPackageVos);
    }

    @PostMapping("addItem")
    @ApiOperation("添加商品")
    public ResponseEntity<?> addItem(@RequestBody @Valid UserPackageAddDTO dto){
        Objects.requireNonNull(bizItemService.getById(dto.getItemId()),"itemId有误");
        bizUserPackageService.packageAddItem(dto);
        return ResponseEntity.success();
    }

    @ApiOperation("移除整个购物袋")
    @PutMapping("cancelAdd/{packageId}")
    public ResponseEntity<?> cancelAdd(@PathVariable Long packageId){
        bizUserPackageService.removeById(packageId);
        return ResponseEntity.success();
    }
}
