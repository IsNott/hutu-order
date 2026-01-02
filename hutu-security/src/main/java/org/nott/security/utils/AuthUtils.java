package org.nott.security.utils;

import org.nott.common.utils.SpringContextUtil;
import org.nott.security.entity.LoginUserDetails;
import org.nott.security.entity.UserInfo;
import org.nott.security.service.JwtTokenServiceImpl;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author Nott
 * @date 2025-12
 */
public class AuthUtils {

   public static UserInfo getCurrentUser() {
       ServletRequestAttributes attributes = (ServletRequestAttributes)
               RequestContextHolder.getRequestAttributes();
       JwtTokenServiceImpl service = SpringContextUtil.getBean(JwtTokenServiceImpl.class);
       LoginUserDetails loginUser = service.getLoginUser(attributes.getRequest());
       return loginUser.getUserInfo();
   }
}
