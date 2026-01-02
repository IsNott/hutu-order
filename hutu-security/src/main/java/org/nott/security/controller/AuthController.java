package org.nott.security.controller;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.nott.common.ResponseEntity;
import org.nott.security.entity.UserInfo;
import org.nott.security.service.JwtTokenServiceImpl;
import org.nott.security.entity.LoginForm;
import org.nott.security.entity.LoginUserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * @author Nott
 * @date 2025-12
 */
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@ApiOperation(value = "认证授权接口", tags = "认证授权接口")
public class AuthController {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Resource
    private JwtTokenServiceImpl jwtTokenService;

    @PostMapping("/login")
    @ApiOperation(value = "登录接口", notes = "登录接口")
    public ResponseEntity<?> login(@RequestBody LoginForm loginForm){
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 获取数据
        LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
        String jwt = jwtTokenService.createTokenJwt(loginUserDetails);
        return ResponseEntity.successData(jwt);
    }

    @PostMapping("/info")
    @ApiOperation(value = "用户信息接口", notes = "用户信息接口")
    public ResponseEntity<?> info(HttpServletRequest request) throws UnsupportedEncodingException {
        LoginUserDetails user = jwtTokenService.getLoginUser(request);
        UserInfo userInfo = user.getUserInfo();
        return ResponseEntity.successData(userInfo);
    }

    @GetMapping("/menu")
    @ApiOperation(value = "用户菜单接口", notes = "用户菜单接口")
    public ResponseEntity<?> menu(HttpServletRequest request) throws UnsupportedEncodingException {
        LoginUserDetails user = jwtTokenService.getLoginUser(request);
        UserInfo userInfo = user.getUserInfo();
        //TODO 根据ROLE获取菜单
        return ResponseEntity.successData("");
    }
}
