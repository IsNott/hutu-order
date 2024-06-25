package org.nott.vo;

import lombok.Data;

/**
 * @author Nott
 * @date 2024-6-14
 */

@Data
public class UserLoginInfoVo {

    private boolean alreadyRegister;

    private String token;

    private String username;

    private String avatarUrl;

    private Integer gender;
}
