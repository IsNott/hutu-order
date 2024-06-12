package org.nott.admin.controller;

import cn.dev33.satoken.stp.StpUtil;
import org.nott.common.ResponseEntity;
import org.nott.service.service.SysUserService;
import org.nott.vo.SysUserInfoVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author nott
 * @since 2024-06-07
 */
@RestController
@RequestMapping("/sys/user/")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    @PostMapping("userInfo")
    public ResponseEntity<?> userInfo(){
        Object loginId = StpUtil.getLoginId();
        SysUserInfoVo vo = sysUserService.getUserInfoByLoginId(loginId);
        return ResponseEntity.successData(vo);
    }

}
