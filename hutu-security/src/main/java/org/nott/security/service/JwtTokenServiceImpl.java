package org.nott.security.service;

/**
 * @author Nott
 * @date 2025-12
 */


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nott.common.exception.HutuBizException;
import org.nott.common.redis.RedisUtils;
import org.nott.security.entity.LoginUserDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


/**
 * @author GJQ
 * @date 2023/6/28 10:19
 * jwt存储逻辑层
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class JwtTokenServiceImpl {


    //  redis工具类
    private final RedisUtils redisUtil;


    //  过期时间
    @Value("${security.jwt.expire:3600}")
    private Integer EXPIRES_TIME;


    @Value("${security.jwt.secret:123456}")
    private String secretKey;


    @Value("${security.jwt.redis-key-prefix:login_token_key:}")
    private String LOGIN_TOKEN_KEY;


    @Value("${security.jwt.header:Token}")
    public String TOKEN_HEADER;


    /**
     * 令牌前缀
     */
    @Value("${security.jwt.token-prefix:Bearer }")
    public String TOKEN_PREFIX;


    /**
     * 创建token令牌
     *
     * @param loginUserDetails
     */
    public String createTokenJwt(LoginUserDetails loginUserDetails) {
        Map<String, Object> headerClaims = new HashMap<>();
        headerClaims.put("alg", "HS256");
        headerClaims.put("typ", "jwt");
        String uuid = UUID.randomUUID().toString();
        // 创建jwt
        String jwt = JWT.create().withHeader(headerClaims)
                //设置签发人
                .withIssuer(loginUserDetails.getUsername())
                //签发时间
                .withIssuedAt(new Date())
                // uuid
                .withClaim(LOGIN_TOKEN_KEY, uuid)
                //自定义声明
                .withClaim("username", loginUserDetails.getUsername())
                //签名算法 使用HS256
                .sign(Algorithm.HMAC256(secretKey));
        //  存储token uuid
        loginUserDetails.setToken(uuid);
        //  刷新/存储token
        refreshToken(loginUserDetails);
        return TOKEN_PREFIX + jwt;
    }


    /**
     * redis存储的token加个前缀
     *
     * @param request
     * @return key
     */
    public String getToken(HttpServletRequest request) throws UnsupportedEncodingException {
        //获取未解码的token
        String encoderToken = request.getHeader(TOKEN_HEADER);
        // 判断是否为null
        if (encoderToken != null && !encoderToken.isEmpty()) {
            // 解码
            String tokenBearer = URLDecoder.decode(encoderToken, "UTF-8");
            if (tokenBearer != null && tokenBearer.startsWith(TOKEN_PREFIX)) {
                //  拆分token
                return tokenBearer.replace(TOKEN_PREFIX, "");
            }
        }
        return null;
    }

    public String getReqOriginToken(HttpServletRequest request) {
        //获取未解码的token
        String token = request.getHeader(TOKEN_HEADER);
        return token;
    }


    /**
     * 获取token的key
     *
     * @param uuid uuid
     * @return key
     */
    private String getTokenKey(String uuid) {
        return LOGIN_TOKEN_KEY + uuid;
    }


    /**
     * 如果距离过期时间小于时效的一半
     * 刷新令牌有效期(刷新redis)
     */
    public void refreshToken(LoginUserDetails loginUserDetails) {
        //  获取token
        String tokenKey = getTokenKey(loginUserDetails.getToken());
        //  token的剩余过期时间
        Long jwtTokenExpire = redisUtil.getExpire(tokenKey);
        //  判断token是否临近过期
        if (jwtTokenExpire <= (EXPIRES_TIME / 2)) {
            //  刷新token
            redisUtil.set(tokenKey, loginUserDetails, EXPIRES_TIME);
        }
    }


    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public LoginUserDetails getLoginUser(HttpServletRequest request) {
        // 获取请求携带的令牌
        try {
            String token = getToken(request);
            if (token != null && !token.isEmpty()) {
                DecodedJWT jwt = parseToken(token);
                // 获取uuid
                String uuid = jwt.getClaim(LOGIN_TOKEN_KEY).asString();
                // 获取token redis的key
                String userKey = getTokenKey(uuid);
                // 查询redis的值
                return (LoginUserDetails) redisUtil.get(userKey);
            }
        } catch (Exception e) {
            log.error("获取用户信息异常'{}'", e.getMessage());
            throw new HutuBizException("获取用户信息异常");
        }
        return null;
    }


    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private DecodedJWT parseToken(String token) {
        //  创建校验器
        JWTVerifier build = JWT.require(Algorithm.HMAC256(secretKey)).build();
        //  校验token
        DecodedJWT verify = build.verify(token);
        return verify;


    }
}
