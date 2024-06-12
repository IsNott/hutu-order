package interceptor;

import cn.dev33.satoken.stp.StpUtil;
import constant.SecurityConstants;
import lombok.extern.slf4j.Slf4j;
import org.nott.common.ResponseEntity;
import org.nott.common.handler.HttpHandler;
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

    private final AntPathMatcher matcher = new AntPathMatcher();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        log.info("AuthorizationInterceptor execute");
        String requestURI = request.getRequestURI();

        if (matcher.match(SecurityConstants.ERROR_URL,requestURI)) {
            HttpHandler.writeResponse(ResponseEntity.failure("系统异常", 500), response);
            return false;
        }

        for (String url : SecurityConstants.PERMITTED_URL) {
            if (matcher.match(url, requestURI)) {
                return true;
            }
        }

        boolean admin = StpUtil.hasRole(SecurityConstants.ADMIN_ROLE_NAME);

        if (admin) {
            return true;
        }

        if (requestURI.startsWith(SecurityConstants.ADMIN_REQUEST_SUFFIX)) {
            requestURI = requestURI.replaceAll(SecurityConstants.ADMIN_REQUEST_SUFFIX, "").replaceAll("/", ".");
            StpUtil.checkPermission(requestURI);
        }

        return true;
    }
}
