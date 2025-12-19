package org.nott.security.filter;

import org.nott.common.exception.UnAuthorizedException;
import org.nott.security.service.JwtTokenServiceImpl;
import org.nott.security.entity.LoginUserDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author Nott
 * @date 2025-12-01
 */
@Service
public class JwtTokenFilter extends OncePerRequestFilter {

    @Value("${spring.security.load:true}")
    private boolean load;

    private static final AntPathMatcher pathMatcher = new AntPathMatcher();

    private static final String[] PERMIT_ALL_PATTERNS = {
            "/auth/login",
            "/auth/logout",
            "/public/**",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/webjars/**"
    };

    private final JwtTokenServiceImpl jwtTokenService;

    public JwtTokenFilter(JwtTokenServiceImpl jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        boolean shouldSkip = Arrays.stream(PERMIT_ALL_PATTERNS)
                .anyMatch(pattern -> pathMatcher.match(pattern, requestURI));

        if (shouldSkip || !load) {
            chain.doFilter(request, response);
            return;
        }

        LoginUserDetails loginUser = jwtTokenService.getLoginUser(request);

        //  校验token是否正确
        if (loginUser != null) {
            jwtTokenService.refreshToken(loginUser);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
            // 为details属性赋值
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            // 将认证信息存储在 SecurityContextHolder 中
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            //  转发给下一个过滤器
            chain.doFilter(request, response);
            return;
        }
        throw new BadCredentialsException("Unauthorized");
    }
}
