package org.nott.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.service.api.IBizUserPackageService;
import org.nott.vo.UserPackageVo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author nott
 * @since 2024-06-07
 */
@Api("购物袋管理")
@RestController
@RequestMapping("/sys/bizUserPackage")
public class SysBizUserPackageController {

    @Resource
    private IBizUserPackageService bizUserPackageService;

    @ApiOperation("查询用户购物袋")
    @PostMapping("/query/{userId}")
    public ResponseEntity<?> queryPackage(@PathVariable Long userId){
        List<UserPackageVo> userPackageVos = bizUserPackageService.queryPackageInfoByUserId(userId);
        return ResponseEntity.successData(userPackageVos);
    }

}
