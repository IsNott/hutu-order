package org.nott.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.nott.dto.AdminLoginDTO;
import org.nott.dto.SysUserInfoDTO;
import org.nott.model.SysUser;
import org.nott.vo.SysUserInfoVo;

public interface SysUserService extends IService<SysUser> {

    SysUser findUserByName(String name);

    SysUserInfoVo getUserInfoByLoginId(Object loginId);

    void updateUserInfo(Object loginId,SysUserInfoDTO userInfoDTO);
}
