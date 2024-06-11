package org.nott.admin.service.impl;

import cn.dev33.satoken.stp.StpInterface;
import org.nott.admin.mapper.SysPermissionMapper;
import org.nott.model.SysPermission;
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

    @Override
    public List<String> getPermissionList(Object loginId, String loginKey) {
        List<SysPermission> permission = sysPermissionMapper.getPermissionByUserId(loginId);
        return permission.stream().map(SysPermission::getPermissionName).collect(Collectors.toList());
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginKey) {
        return null;
    }
}
