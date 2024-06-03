package org.nott.web.controller;

import org.nott.common.ResponseEntity;
import org.nott.dto.UserPackageAddDTO;
import org.nott.dto.UserPackageQueryDTO;
import org.nott.vo.UserPackageVo;
import org.nott.web.service.IBizItemService;
import org.nott.web.service.IBizUserPackageService;
import org.nott.web.service.IBizUserService;
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
@RestController
@RequestMapping("/bizUserPackage")
public class BizUserPackageController {

    @Resource
    private IBizUserPackageService bizUserPackageService;

    @Resource
    private IBizUserService bizUserService;

    @Resource
    private IBizItemService bizItemService;

    @PostMapping("/query")
    public ResponseEntity<?> queryPackage(@RequestBody @Valid UserPackageQueryDTO dto){
        List<UserPackageVo> userPackageVos = bizUserPackageService.queryPackageInfoByUserId(dto);
        return ResponseEntity.successData(userPackageVos);
    }

    @PostMapping("addItem")
    public ResponseEntity<?> addItem(@RequestBody @Valid UserPackageAddDTO dto){
        Objects.requireNonNull(bizItemService.getById(dto.getItemId()),"itemId有误");
        Objects.requireNonNull(bizUserService.getById(dto.getUserId()),"用户id有误");
        bizUserPackageService.packageAddItem(dto);
        return ResponseEntity.success();
    }

    @PutMapping("cancelAdd/{packageId}")
    public ResponseEntity<?> cancelAdd(@PathVariable Long packageId){
        bizUserPackageService.removeById(packageId);
        return ResponseEntity.success();
    }
}
