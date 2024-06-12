package org.nott.service.service;

import org.nott.dto.AdminLoginDTO;
import org.nott.model.SysUser;

public interface SysUserService {

    SysUser findUserByName(String name);

}
