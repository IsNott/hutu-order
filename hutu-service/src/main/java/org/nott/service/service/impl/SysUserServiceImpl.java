package org.nott.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.nott.common.utils.HutuUtils;
import org.nott.dto.SysUserInfoDTO;
import org.nott.model.SysRole;
import org.nott.service.mapper.SysRoleMapper;
import org.nott.service.mapper.SysUserMapper;
import org.nott.service.service.SysUserService;
import org.nott.model.SysUser;
import org.nott.vo.SysUserInfoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Nott
 * @date 2024-6-6
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public SysUser findUserByName(String username) {
        return sysUserMapper.getUserByUsername(username);
    }

    @Override
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

    @Override
    public void updateUserInfo(Object loginId, SysUserInfoDTO userInfoDTO) {
        SysUser sysUser = sysUserMapper.selectById(loginId + "");

    }

}
