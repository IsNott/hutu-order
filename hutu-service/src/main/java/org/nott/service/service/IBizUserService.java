package org.nott.service.service;

import org.nott.model.BizUser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.nott.vo.ExternalUserInfo;
import org.nott.vo.UserLoginInfoVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author nott
 * @since 2024-05-24
 */
public interface IBizUserService extends IService<BizUser> {

    BizUser getUserByOpenId(String openId);

    UserLoginInfoVo registerUser(ExternalUserInfo userInfo);

    UserLoginInfoVo loginUser(ExternalUserInfo userInfo);
}
