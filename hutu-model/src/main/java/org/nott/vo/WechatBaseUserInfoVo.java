package org.nott.vo;

import lombok.Data;

/**
 * @author Nott
 * @date 2024-6-14
 */

@Data
public class WechatBaseUserInfoVo extends ExternalBaseUserInfo {

    private String openid;

    private String nickname;

    private Integer sex;

    private String province;

    private String city;

    private String country;

    private String headimgurl;

    private String privilege;

    private String unionid;
}
