package org.nott.service.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import org.nott.service.mapper.SysUserMapper;
import org.nott.service.service.SysUserService;
import org.nott.common.exception.PasswordNotMatchesException;
import org.nott.common.exception.UserNotFoundException;
import org.nott.dto.AdminLoginDTO;
import org.nott.model.SysUser;
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



    @Override
    public SysUser findUserByName(String username) {
        return sysUserMapper.getUserByUsername(username);
    }

}
