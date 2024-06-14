package org.nott.external.wechat.constant;

/**
 * @author Nott
 * @date 2024-6-14
 */

public class WechatApiConstant {

    public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid={APPID}&secret={SECRET}&code={CODE}&grant_type=authorization_code";

    public static final String REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid={APPID}&grant_type=refresh_token&refresh_token={REFRESH_TOKEN}";

    public static final String CHECK_TOKEN_URL = "https://api.weixin.qq.com/sns/auth?access_token={ACCESS_TOKEN}&openid={OPENID}";

    public static final String GET_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token={ACCESS_TOKEN}&openid={OPENID}";

}