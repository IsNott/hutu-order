package org.nott.service.admin;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.nott.dto.SysUserInfoDTO;
import org.nott.model.SysRole;
import org.nott.service.admin.SysUserService;
import org.nott.service.mapper.admin.SysRoleMapper;
import org.nott.service.mapper.admin.SysUserMapper;
import org.nott.model.SysUser;
import org.nott.vo.SysUserInfoVo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Nott
 * @date 2024-6-6
 */
@Service
public class SysUserService extends ServiceImpl<SysUserMapper, SysUser>{

    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysRoleMapper sysRoleMapper;

     
    public SysUser findUserByName(String username) {
        return sysUserMapper.getUserByUsername(username);
    }

     
    public SysUserInfoVo getUserInfoByLoginId(Object loginId) {
        SysUser user = getById(loginId + "");
        SysUserInfoVo vo = new SysUserInfoVo();
        vo.setAvatar(user.getAvatarUrl());
        vo.setName(user.getUsername());
        vo.setIntroduction("");
        List<SysRole> roleList = sysRoleMapper.getRoleByUserId(loginId);
        List<String> roles = roleList.stream().map(SysRole::getRoleName).collect(Collectors.toList());
        vo.setRoles(roles);
        return vo;
    }

     
    public void updateUserInfo(Object loginId, SysUserInfoDTO userInfoDTO) {

    }

}
