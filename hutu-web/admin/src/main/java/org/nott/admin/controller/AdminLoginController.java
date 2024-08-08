package org.nott.admin.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.nott.common.exception.PasswordNotMatchesException;
import org.nott.common.exception.UserNotFoundException;
import org.nott.model.SysUser;
import org.nott.service.service.SysUserService;
import org.nott.common.ResponseEntity;
import org.nott.dto.AdminLoginDTO;
import org.nott.vo.AdminTokenVo;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
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
@Api("后台登录")
@RestController
@RequestMapping("/sys/admin")
public class AdminLoginController {

    @Resource
    private SysUserService sysUserService;

    @Resource
    private PasswordEncoder passwordEncoder;
    @ApiOperation("登录")
    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody @Valid AdminLoginDTO dto) {
        String username = dto.getUsername();
        SysUser userByName = sysUserService.findUserByName(username);
        if(userByName == null){
            throw new UserNotFoundException("找不到对应用户");
        }
        boolean matches = passwordEncoder.matches(dto.getPassword(), userByName.getPassword());
        if(!matches){
            throw new PasswordNotMatchesException("密码错误");
        }
        StpUtil.setLoginId(userByName.getId());
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        AdminTokenVo vo = new AdminTokenVo();
        BeanUtils.copyProperties(tokenInfo, vo);
        return ResponseEntity.successData(vo);
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
