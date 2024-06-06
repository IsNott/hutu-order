package org.nott.admin.service;

import org.nott.dto.AdminLoginDTO;
import org.nott.model.SysUser;

public interface SysUserService {

    SysUser findUserByName(String name);

    void adminLogin(AdminLoginDTO dto);
}
