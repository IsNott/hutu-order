package org.nott.admin.controller;

import cn.dev33.satoken.stp.StpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.nott.common.ResponseEntity;
import org.nott.common.annotation.JustLogin;
import org.nott.common.utils.HutuUtils;
import org.nott.dto.SysUserInfoDTO;
import org.nott.model.SysUser;
import org.nott.service.admin.SysUserService;
import org.nott.vo.SysUserInfoVo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author nott
 * @since 2024-06-07
 */
@Api("系统用户管理")
@RestController
@RequestMapping("/sys/user/")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @ApiOperation("当前登录信息")
    @PostMapping("userInfo")
    public ResponseEntity<?> userInfo() {
        Object loginId = StpUtil.getLoginId();
        SysUserInfoVo vo = sysUserService.getUserInfoByLoginId(loginId);
        return ResponseEntity.successData(vo);
    }

    @ApiOperation("更新个人信息")
    @JustLogin
    @PostMapping("profile")
    public ResponseEntity<?> profile(@RequestBody SysUserInfoDTO userInfoDTO) {
        Object loginId = StpUtil.getLoginId();
        SysUser sysUser = sysUserService.getById(loginId + "");
        String encodePassword = passwordEncoder.encode(userInfoDTO.getPassword());
        userInfoDTO.setPassword(encodePassword);
        HutuUtils.requireNotNull(sysUser);
        HutuUtils.copyProperties(userInfoDTO,sysUser);
        sysUserService.updateById(sysUser);
        StpUtil.logout();
        return ResponseEntity.success();
    }

}
