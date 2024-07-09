package org.nott.service.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.nott.common.redis.RedisUtils;
import org.nott.common.utils.HutuUtils;
import org.nott.dto.UserLoginDTO;
import org.nott.dto.UserProfileDTO;
import org.nott.dto.UserRegisterDTO;
import org.nott.model.BizUser;
import org.nott.service.mapper.BizUserMapper;
import org.nott.service.service.IBizUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.nott.vo.ExternalUserInfo;
import org.nott.vo.UserLoginInfoVo;
import org.nott.vo.WechatUserInfoVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
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

    @Resource
    private RedisUtils redisUtils;

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
        user.setPhone(userInfo.getPhone());
        this.save(user);
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

    @Override
    public UserLoginInfoVo register(UserRegisterDTO dto) {
        String code = dto.getCode();
        String openId = redisUtils.get(code, String.class);
        ExternalUserInfo info = new WechatUserInfoVo();
        HutuUtils.copyProperties(dto,info);
        info.setOpenId(openId);

        return registerUser(info);
    }

    @Override
    public UserLoginInfoVo loginByPhone(UserLoginDTO dto) {
        HutuUtils.requireNotNull(dto.getPhone(),"手机号不能为空");
        LambdaQueryWrapper<BizUser> wrapper = new LambdaQueryWrapper<BizUser>().eq(BizUser::getPhone,dto.getPhone());
        BizUser bizUser = this.getOne(wrapper);
        if(HutuUtils.isEmpty(bizUser)){
            WechatUserInfoVo userRegisterDTO = HutuUtils.transToVo(dto, WechatUserInfoVo.class);
            return this.registerUser(userRegisterDTO);
        }
        return login(bizUser);
    }

    @Override
    public UserLoginInfoVo updateUserInfo(UserProfileDTO dto) {
        Serializable loginId = (Serializable) StpUtil.getLoginId();
        BizUser user = getById(loginId);
        HutuUtils.requireNotNull(user,"当前登录信息不存在");
        String nickName = dto.getNickName();
        Integer gender = dto.getGender();
        String avatarUrl = dto.getAvatarUrl();
        if(HutuUtils.isNotEmpty(nickName)){
            user.setUsername(nickName);
        }
        if(HutuUtils.isNotEmpty(gender)){
            user.setGender(gender);
        }
        if(HutuUtils.isNotEmpty(avatarUrl)){
            user.setAvatarUrl(avatarUrl);
        }
        this.updateById(user);

        UserLoginInfoVo vo = HutuUtils.transToVo(dto, UserLoginInfoVo.class);
        vo.setToken(StpUtil.getTokenValue());
        vo.setAlreadyRegister(true);
        vo.setUsername(nickName);

        return vo;
    }

    @Override
    public String login(String openId, BizUser user) {
        this.login(user);
        return StpUtil.getTokenValue();
    }

    private UserLoginInfoVo login(BizUser user){
        Long userId = user.getId();
        user.setLastLogTime(new Date());
        this.updateById(user);
        StpUtil.setLoginId(userId);
        UserLoginInfoVo userLoginInfoVo = new UserLoginInfoVo();
        HutuUtils.copyProperties(user,userLoginInfoVo);
        userLoginInfoVo.setAlreadyRegister(true);
        userLoginInfoVo.setToken(StpUtil.getTokenValue());
        return userLoginInfoVo;
    }
}
