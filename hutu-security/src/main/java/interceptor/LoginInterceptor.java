package interceptor;

import cn.dev33.satoken.stp.StpUtil;
import constant.SecurityConstants;
import lombok.extern.slf4j.Slf4j;
import org.nott.common.ResponseEntity;
import org.nott.common.handler.HttpHandler;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 基于SpringMvc的拦截器
 *
 * @author Nott
 * @date 2024-6-6
 */
@Order(1)
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("Request Url: [{}]", requestURI);
        if (SecurityConstants.ERROR_URL.equals(requestURI)) {
            HttpHandler.writeResponse(ResponseEntity.failure("系统异常", 500), response);
            return false;
        }

        for (String url : SecurityConstants.PERMITTED_URL) {
            if (url.equals(requestURI)) {
                return true;
            }
        }
        String header = request.getHeader(StpUtil.getTokenName());
        if (header == null) {
            HttpHandler.writeResponse(ResponseEntity.failure("请先登录", 401), response);
            return false;
        }
        StpUtil.checkLogin();
        return true;
    }
}