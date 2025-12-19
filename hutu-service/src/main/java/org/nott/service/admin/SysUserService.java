package org.nott.service.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.nott.common.utils.HutuUtils;
import org.nott.security.entity.LoginUserDetails;
import org.nott.security.entity.UserInfo;
import org.nott.service.mapper.admin.SysUserMapper;
import org.nott.model.SysUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Nott
 * @date 2024-6-6
 */
@Service
public class SysUserService extends ServiceImpl<SysUserMapper, SysUser> implements UserDetailsService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername, name);
        SysUser sysUser = this.getOne(wrapper);
        if(HutuUtils.isEmpty(sysUser)){
            throw new UsernameNotFoundException("用户不存在");
        }
        UserInfo userInfo = HutuUtils.transToObject(sysUser, UserInfo.class);
        userInfo.setUserId(sysUser.getId());
        LoginUserDetails details = new LoginUserDetails();
        details.setUserInfo(userInfo);
        return details;
    }
}
