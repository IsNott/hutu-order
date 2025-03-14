package org.nott.web.controller;

import cn.dev33.satoken.stp.StpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.dto.UserLoginDTO;
import org.nott.dto.UserProfileDTO;
import org.nott.dto.UserRegisterDTO;
import org.nott.service.service.IBizUserService;
import org.nott.vo.UserBalanceVo;
import org.nott.vo.UserLoginInfoVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author nott
 * @since 2024-05-24
 */
@Api(tags = "用户")
@RestController
@RequestMapping("/bizUser")
public class BizUserController {

    @Resource
    private IBizUserService bizUserService;

    @Deprecated
    @ApiOperation("用户注册")
    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody UserRegisterDTO dto){
        UserLoginInfoVo vo = bizUserService.register(dto);
        return ResponseEntity.successData(vo);
    }

    @ApiOperation("用户登录")
    @PostMapping("login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginDTO dto){
        UserLoginInfoVo vo = bizUserService.loginByPhone(dto);
        return ResponseEntity.successData(vo);
    }

    @ApiOperation("用户个人信息更新")
    @PostMapping("profile")
    public ResponseEntity<?> updateProfile(@RequestBody UserProfileDTO dto){
        UserLoginInfoVo vo = bizUserService.updateUserInfo(dto);
        return ResponseEntity.successData(vo);
    }

    @ApiOperation("登出")
    @GetMapping("logout")
    public ResponseEntity<?> logout(){
        StpUtil.logout();
        return ResponseEntity.success();
    }

    @ApiOperation("我的余额")
    @GetMapping("myBalance")
    public ResponseEntity<?> myBalance(){
        UserBalanceVo vo = bizUserService.queryMyBalance(StpUtil.getLoginIdAsLong());
        return ResponseEntity.successData(vo);
    }

}
