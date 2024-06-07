package org.nott.admin.controller;

import cn.dev33.satoken.stp.StpUtil;
import org.nott.admin.service.SysUserService;
import org.nott.common.ResponseEntity;
import org.nott.dto.AdminLoginDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author Nott
 * @date 2024-6-6
 */

@RestController
@RequestMapping("/sys/admin")
public class AdminLoginController {

    @Resource
    private SysUserService sysUserService;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody @Valid AdminLoginDTO dto){
        sysUserService.adminLogin(dto);
        return ResponseEntity.successData(StpUtil.getTokenInfo());
    }

    @PostMapping("logout")
    public ResponseEntity<?> logout(){
        StpUtil.logout();
        return ResponseEntity.success();
    }

    @PostMapping("test")
    public ResponseEntity<?> testToken(){
        StpUtil.checkLogin();
        return ResponseEntity.success();
    }
}
