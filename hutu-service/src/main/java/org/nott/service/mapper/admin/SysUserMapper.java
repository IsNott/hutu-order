package org.nott.service.mapper.admin;

import com.github.yulichang.base.MPJBaseMapper;
import org.nott.model.SysUser;

public interface SysUserMapper extends MPJBaseMapper<SysUser> {
    SysUser getUserByUsername(String username);
}
