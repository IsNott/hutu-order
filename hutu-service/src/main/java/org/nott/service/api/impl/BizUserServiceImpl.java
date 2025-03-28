package org.nott.service.api.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.nott.common.exception.HutuBizException;
import org.nott.common.redis.RedisUtils;
import org.nott.common.utils.HutuUtils;
import org.nott.dto.UserLoginDTO;
import org.nott.dto.UserProfileDTO;
import org.nott.dto.UserRegisterDTO;
import org.nott.enums.UserPointUseEnum;
import org.nott.model.BizUser;
import org.nott.service.api.IBizUserPointLogService;
import org.nott.service.mapper.api.BizUserMapper;
import org.nott.service.api.IBizUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.nott.vo.ExternalBaseUserInfo;
import org.nott.vo.UserBalanceVo;
import org.nott.vo.UserLoginInfoVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.math.BigDecimal;
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

    @Resource
    private IBizUserPointLogService bizUserPointLogService;

    @Resource
    private BizUserMapper bizUserMapper;

    @Override
    public BizUser getUserByOpenId(String openId) {
        LambdaQueryWrapper<BizUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BizUser::getOpenId, openId);
        return this.getOne(wrapper);
    }


    @Override
    public UserLoginInfoVo registerUser(ExternalBaseUserInfo userInfo) {
        BizUser user = new BizUser();
        user.setLoginCount(1);
        user.setRegistTime(new Date());
        user.setOpenId(userInfo.getOpenId());
        user.setLastLogTime(new Date());
        user.setUsername(StringUtils.isNotEmpty(userInfo.getNickName()) ? userInfo.getNickName() : "用户" + DigestUtils.md5Hex((new Date().getTime() + "")).substring(0, 10));
        user.setAvatarUrl(userInfo.getAvatarUrl());
        user.setGiftBalance(BigDecimal.ZERO);
        user.setActualBalance(BigDecimal.ZERO);
        user.setPhone(userInfo.getPhone());
        this.save(user);
        return this.login(user);
    }

    @Override
    public UserLoginInfoVo loginUser(ExternalBaseUserInfo userInfo) {
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
        ExternalBaseUserInfo info = HutuUtils.transToObject(dto, ExternalBaseUserInfo.class);
        info.setOpenId(openId);
        return registerUser(info);
    }

    @Override
    public UserLoginInfoVo loginByPhone(UserLoginDTO dto) {
        HutuUtils.requireNotNull(dto.getPhone(),"手机号不能为空");
        LambdaQueryWrapper<BizUser> wrapper = new LambdaQueryWrapper<BizUser>().eq(BizUser::getPhone,dto.getPhone());
        BizUser bizUser = this.getOne(wrapper);
        if(HutuUtils.isEmpty(bizUser)){
            ExternalBaseUserInfo userRegisterDTO = HutuUtils.transToObject(dto, ExternalBaseUserInfo.class);
            return this.registerUser(userRegisterDTO);
        }
        return login(bizUser);
    }

    @Override
    public UserLoginInfoVo updateUserInfo(UserProfileDTO dto) {
        Serializable loginId = (Serializable) StpUtil.getLoginId();
        BizUser user = getById(loginId);
        HutuUtils.requireNotNull(user,"当前登录信息不存在");
        String username = dto.getUsername();
        Integer gender = dto.getGender();
        String avatarUrl = dto.getAvatarUrl();
        String phone = dto.getPhone();
        if(HutuUtils.isNotEmpty(username)){
            user.setUsername(username);
        }
        if(HutuUtils.isNotEmpty(gender)){
            user.setGender(gender);
        }
        if(HutuUtils.isNotEmpty(avatarUrl)){
            user.setAvatarUrl(avatarUrl);
        }
        if(HutuUtils.isNotEmpty(phone)){
            user.setPhone(phone);
        }
        this.updateById(user);

        UserLoginInfoVo vo = HutuUtils.transToObject(dto, UserLoginInfoVo.class);
        vo.setToken(StpUtil.getTokenValue());
        vo.setAlreadyRegister(true);

        return vo;
    }

    @Override
    public UserBalanceVo queryMyBalance(long id) {
        BizUser user = this.getById(id);
        HutuUtils.requireNotNull(user, "没有找到用户");
        UserBalanceVo vo = new UserBalanceVo();
        vo.setActualBalance(user.getActualBalance());
        vo.setGiftBalance(user.getGiftBalance());
        vo.total();
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

    @Override
    public Long queryUsablePoint() {
        long id = StpUtil.getLoginIdAsLong();
        LambdaQueryWrapper<BizUser> wrapper = new LambdaQueryWrapper<BizUser>().eq(BizUser::getId, id);
        BizUser user = this.getOne(wrapper);
        return HutuUtils.getIfValue(user.getPoint(), 0L);
    }

    @Override
    public void usePoint(String fee,Long originPoint, Long usePoint) {
        long id = StpUtil.getLoginIdAsLong();
        HutuUtils.requireTrue(originPoint >= usePoint,"原积分不足");
        int affectRow = bizUserMapper.costPointByCas(id, originPoint, usePoint);
        if(affectRow == 0){
            throw new HutuBizException("原积分已被修改，请刷新页面重试");
        }
        bizUserPointLogService.saveLog(fee,usePoint, UserPointUseEnum.USER_Active);
    }
}
