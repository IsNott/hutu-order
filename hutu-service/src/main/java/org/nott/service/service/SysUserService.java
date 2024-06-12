package org.nott.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.nott.dto.AdminLoginDTO;
import org.nott.model.SysUser;
import org.nott.vo.SysUserInfoVo;

public interface SysUserService {

    SysUser findUserByName(String name);

    SysUserInfoVo getUserInfoByLoginId(Object loginId);
}
