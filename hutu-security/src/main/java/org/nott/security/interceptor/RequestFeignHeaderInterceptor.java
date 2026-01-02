package org.nott.security.interceptor;


import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.nott.common.utils.HutuUtils;
import org.nott.security.service.JwtTokenServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * Feign请求拦截器，传递请求头中的Token
 * @Author tasteTheWorld
 * @date 12月
 * version 1.0.0
 */

@Component
public class RequestFeignHeaderInterceptor implements RequestInterceptor {

    @Value("${security.jwt.header:Token}")
    public String TOKEN_HEADER;

    @Resource
    private JwtTokenServiceImpl jwtTokenService;

    @Override
    public void apply(RequestTemplate template) {
        String token = jwtTokenService.getReqOriginToken(HutuUtils.getCurrentRequest());
        template.header(TOKEN_HEADER, token);
    }
}
