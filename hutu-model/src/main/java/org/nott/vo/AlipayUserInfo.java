package org.nott.vo;

import lombok.Data;

/**
 * @author Nott
 * @date 2024-6-14
 */

@Data
public class AlipayUserInfo extends ExternalUserInfo {

    private String avatar;

    private String city;

    private String nickName;

    private String province;

    private String userId;
}