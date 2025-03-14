package org.nott.service.service;

import org.nott.dto.UserLoginDTO;
import org.nott.dto.UserProfileDTO;
import org.nott.dto.UserRegisterDTO;
import org.nott.model.BizUser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.nott.vo.ExternalBaseUserInfo;
import org.nott.vo.UserBalanceVo;
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

    String login(String openId,BizUser user);

    UserLoginInfoVo registerUser(ExternalBaseUserInfo userInfo);

    UserLoginInfoVo loginUser(ExternalBaseUserInfo userInfo);

    UserLoginInfoVo register(UserRegisterDTO dto);

    UserLoginInfoVo loginByPhone(UserLoginDTO dto);

    UserLoginInfoVo updateUserInfo(UserProfileDTO dto);

    UserBalanceVo queryMyBalance(long id);
}
