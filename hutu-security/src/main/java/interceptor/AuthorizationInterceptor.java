package interceptor;

import cn.dev33.satoken.stp.StpUtil;
import constant.SecurityConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Nott
 * @date 2024-6-11
 */
@Order(2)
@Component
@Slf4j
public class AuthorizationInterceptor implements HandlerInterceptor {

    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("AuthorizationInterceptor execute");
        String requestURI = request.getRequestURI();

        if(SecurityConstants.PERMITTED_URL.contains(requestURI) || SecurityConstants.ERROR_URL.equals(requestURI)){
            return true;
        }

        requestURI = requestURI.replaceAll("/sys/","").replaceAll("/",".");

        return StpUtil.hasPermission(requestURI);
    }
}
