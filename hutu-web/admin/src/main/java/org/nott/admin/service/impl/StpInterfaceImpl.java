package org.nott.admin.service.impl;

import cn.dev33.satoken.stp.StpInterface;

import java.util.List;

/**
 * @author Nott
 * @date 2024-6-7
 */
public class StpInterfaceImpl implements StpInterface {

    @Override
    public List<String> getPermissionList(Object loginId, String s) {
        return null;
    }

    @Override
    public List<String> getRoleList(Object loginId, String s) {
        return null;
    }
}
