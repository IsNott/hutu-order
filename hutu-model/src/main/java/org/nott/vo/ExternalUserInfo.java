package org.nott.vo;

import lombok.Data;

/**
 * @author Nott
 * @date 2024-6-14
 */

@Data
public abstract class ExternalUserInfo {

    private String openId;

    private String phone;

    private String nickName;

    private String avatarUrl;

}
