package org.nott.admin.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import org.nott.admin.mapper.SysUserMapper;
import org.nott.admin.service.SysUserService;
import org.nott.common.exception.PasswordNotMatchesException;
import org.nott.common.exception.UserNotFoundException;
import org.nott.dto.AdminLoginDTO;
import org.nott.model.SysUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Nott
 * @date 2024-6-6
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private PasswordEncoder passwordEncoder;


    @Override
    public SysUser findUserByName(String username) {
        return sysUserMapper.getUserByUsername(username);
    }

    @Override
    public void adminLogin(AdminLoginDTO dto) {
        String username = dto.getUsername();
        SysUser userByName = this.findUserByName(username);
        if(userByName == null){
            throw new UserNotFoundException("找不到对应用户");
        }
        boolean matches = passwordEncoder.matches(dto.getPassword(), userByName.getPassword());
        if(!matches){
            throw new PasswordNotMatchesException("密码错误");
        }
        StpUtil.setLoginId(userByName.getId());
    }
}
