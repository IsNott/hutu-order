package org.nott.service.service.impl;

import cn.dev33.satoken.stp.StpInterface;
import org.nott.service.mapper.SysPermissionMapper;
import org.nott.service.mapper.SysRoleMapper;
import org.nott.model.SysPermission;
import org.nott.model.SysRole;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Nott
 * @date 2024-6-7
 */
@Service
public class StpInterfaceImpl implements StpInterface {

    @Resource
    private SysPermissionMapper sysPermissionMapper;

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<String> getPermissionList(Object loginId, String loginKey) {
        List<SysPermission> permission = sysPermissionMapper.getPermissionByUserId(loginId);
        return permission.stream().map(SysPermission::getPermissionName).collect(Collectors.toList());
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginKey) {
        List<SysRole> roles = sysRoleMapper.getRoleByUserId(loginId);
        return roles.stream().map(SysRole::getRoleName).collect(Collectors.toList());
    }
}
