package org.nott.external.wechat.constant;

/**
 * @author Nott
 * @date 2024-6-14
 */

public class WechatApiConstant {

    public interface redisKey{
        String API_NAME = "WECHAT";
        String SESSION = "SESSION_KEY";
        String STABLE_TOKEN = "STABLE_TOKEN";
        String ACCESS_TOKEN = "ACCESS_TOKEN";
        String LOGIN = "WX_LOGIN";
    }

    public static final String MINI_PROGRAM_CHECK_SESSION = "https://api.weixin.qq.com/wxa/checksession?access_token={TOKEN}&signature={SIGN}&openid={OPENID}&sig_method=hmac_sha256";

    public static final String CGI_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?appid={APPID}&secret={SECRET}&grant_type=client_credential";

    public static final String STABLE_TOKEN = "https://api.weixin.qq.com/cgi-bin/stable_token";

    public static final String MINI_PROGRAM_LOGIN = "https://api.weixin.qq.com/sns/jscode2session?appid={APPID}&secret={SECRET}&js_code={CODE}&grant_type=authorization_code";

    public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid={APPID}&secret={SECRET}&code={CODE}&grant_type=authorization_code";

    public static final String REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid={APPID}&grant_type=refresh_token&refresh_token={REFRESH_TOKEN}";

    public static final String CHECK_TOKEN_URL = "https://api.weixin.qq.com/sns/auth?access_token={ACCESS_TOKEN}&openid={OPENID}";

    public static final String PHONE_NUM_URL = "https://api.weixin.qq.com/wxa/business/getuserphonenumber?access_token={ACCESS_TOKEN}";

    public static final String OAUTH_GET_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token={ACCESS_TOKEN}&openid={OPENID}";

}