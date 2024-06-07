package interceptor;

import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import org.nott.common.ResponseEntity;
import org.nott.common.handler.HttpHandler;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 基于SpringMvc的拦截器
 *
 * @author Nott
 * @date 2024-6-6
 */
@Order(1)
@Component
@Slf4j
public class AuthenticInterceptor implements HandlerInterceptor {

    private final static List<String> permittedUrl = Arrays.asList("/sys/admin/login", "/sys/admin/logout", "/error");

    private final static String errorUrl = "/error";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("Request Url: [{}]", requestURI);
        if (errorUrl.equals(requestURI)) {
            HttpHandler.writeResponse(ResponseEntity.failure("系统异常", 500), response);
            return false;
        }

        for (String url : permittedUrl) {
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
