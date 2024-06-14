package org.nott.service.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.nott.model.BizUser;
import org.nott.service.mapper.BizUserMapper;
import org.nott.service.service.IBizUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.nott.vo.ExternalUserInfo;
import org.nott.vo.UserLoginInfoVo;
import org.springframework.stereotype.Service;
import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author nott
 * @since 2024-05-24
 */
@Service
public class BizUserServiceImpl extends ServiceImpl<BizUserMapper, BizUser> implements IBizUserService {

    @Override
    public BizUser getUserByOpenId(String openId) {
        LambdaQueryWrapper<BizUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BizUser::getOpenId, openId);
        return this.getOne(wrapper);
    }

    @Override
    public UserLoginInfoVo registerUser(ExternalUserInfo userInfo) {
        BizUser user = new BizUser();
        user.setLoginCount(1);
        user.setRegistTime(new Date());
        user.setOpenId(userInfo.getOpenId());
        user.setLastLogTime(new Date());
        user.setUsername(userInfo.getNickName());
        user.setAvatarUrl(userInfo.getAvatarUrl());
        return this.login(user);
    }

    @Override
    public UserLoginInfoVo loginUser(ExternalUserInfo userInfo) {
        BizUser user = getUserByOpenId(userInfo.getOpenId());
        Integer loginCount = user.getLoginCount();
        loginCount += 1;
        user.setLoginCount(loginCount);
        if(!user.getUsername().equals(userInfo.getNickName())){
            user.setUsername(userInfo.getNickName());
        }
        if(!user.getAvatarUrl().equals(userInfo.getAvatarUrl())){
            user.setUsername(userInfo.getAvatarUrl());
        }
        return this.login(user);
    }

    private UserLoginInfoVo login(BizUser user){
        Long userId = user.getId();
        user.setLastLogTime(new Date());
        this.updateById(user);
        StpUtil.setLoginId(userId);
        UserLoginInfoVo userLoginInfoVo = new UserLoginInfoVo();
        userLoginInfoVo.setLoginId(userId);
        userLoginInfoVo.setToken(StpUtil.getTokenValue());
        return userLoginInfoVo;
    }
}
