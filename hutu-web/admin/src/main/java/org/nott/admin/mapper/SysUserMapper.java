package org.nott.admin.mapper;

import org.nott.model.SysUser;

public interface SysUserMapper {
    SysUser getUserByUsername(String username);
}
