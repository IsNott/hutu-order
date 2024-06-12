package org.nott.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.nott.model.SysUser;

public interface SysUserMapper extends BaseMapper<SysUser> {
    SysUser getUserByUsername(String username);
}
