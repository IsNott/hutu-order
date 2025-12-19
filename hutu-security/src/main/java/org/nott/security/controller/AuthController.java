package org.nott.security.controller;

import lombok.AllArgsConstructor;
import org.nott.common.ResponseEntity;
import org.nott.security.service.JwtTokenServiceImpl;
import org.nott.security.entity.LoginForm;
import org.nott.security.entity.LoginUserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * @author Nott
 * @date 2025-12-01
 */
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Resource
    private JwtTokenServiceImpl jwtTokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginForm loginForm){
        //  用户名密码校验
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword());
        //  认证
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 获取数据
        LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
        // 获取token 并存储
        String jwt = jwtTokenService.createTokenJwt(loginUserDetails);
        return ResponseEntity.successData(jwt);
    }

    @PostMapping("/info")
    public ResponseEntity<?> info(HttpServletRequest request) throws UnsupportedEncodingException {
        return ResponseEntity.successData(jwtTokenService.getLoginUser(request));
    }

}
