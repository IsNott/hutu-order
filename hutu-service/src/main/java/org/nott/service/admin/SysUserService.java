package org.nott.service.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.nott.common.exception.HutuBizException;
import org.nott.common.utils.HutuUtils;
import org.nott.dto.SysUserDTO;
import org.nott.model.SysRole;
import org.nott.model.SysUserRole;
import org.nott.security.entity.LoginUserDetails;
import org.nott.security.entity.UserInfo;
import org.nott.service.mapper.admin.SysRoleMapper;
import org.nott.service.mapper.admin.SysUserMapper;
import org.nott.model.SysUser;
import org.nott.vo.SysRoleVo;
import org.nott.vo.SysUserVo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Nott
 * @date 2024-6-6
 */
@Service
public class SysUserService extends ServiceImpl<SysUserMapper, SysUser> implements UserDetailsService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysRoleMapper sysRoleMapper;

    public IPage<SysUserVo> queryPage(Integer page, Integer size, SysUserDTO dto) {
        MPJLambdaWrapper<SysUser> wrapper = new MPJLambdaWrapper<SysUser>()
                .selectAll(SysUser.class)
                .orderByDesc(SysUser::getCreateTime);
        Page<SysUserVo> sysUserVoPage = sysUserMapper.selectJoinPage(new Page<>(page, size), SysUserVo.class, wrapper);
        if(HutuUtils.isNotEmpty(sysUserVoPage.getRecords())){
            for (SysUserVo record : sysUserVoPage.getRecords()) {
                MPJLambdaWrapper<SysRole> roleWrapper = new MPJLambdaWrapper<SysRole>()
                        .selectAll(SysRole.class)
                        .innerJoin(SysUserRole.class,  SysUserRole::getRoleId,SysRole::getId)
                        .eq(SysUserRole::getUserId, record.getId());
                List<SysRoleVo> roles = sysRoleMapper.selectJoinList(SysRoleVo.class, roleWrapper);
                record.setRoles(roles.stream().distinct().collect(Collectors.toList()));
            }
        }
        return sysUserVoPage;
    }


    public SysUserVo save(SysUserDTO dto) {
        SysUser entity = HutuUtils.transToObject(dto, SysUser.class);
        entity.setDelFlag(false);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        this.save(entity);
        return HutuUtils.transToObject(entity, SysUserVo.class);
    }

    public SysUserVo update(SysUserDTO dto) {
        Long id = dto.getId();
        SysUser entity = this.getById(id);
        if(HutuUtils.isEmpty(entity)){
            throw new HutuBizException("Entity not found.");
        }
        HutuUtils.copyProperties(dto, entity);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        this.updateById(entity);
        return HutuUtils.transToObject(entity, SysUserVo.class);
    }

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

    public String resetPassword(Long id) {
        SysUser user = this.getById(id);
        HutuUtils.requireNotNull(user, "用户不存在");
        String defaultPwd = "hutu123";
        user.setPassword(passwordEncoder.encode(defaultPwd));
        this.updateById(user);
        return "重置成功，默认密码：" + defaultPwd;
    }
}
