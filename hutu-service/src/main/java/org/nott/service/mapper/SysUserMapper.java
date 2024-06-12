package org.nott.service.mapper;

import org.nott.model.SysUser;

public interface SysUserMapper {
    SysUser getUserByUsername(String username);
}
