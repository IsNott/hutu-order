package org.nott.dto;

import lombok.Data;

/**
 * @author Nott
 * @date 2024-6-25
 */

@Data
public class UserRegisterDTO {

    private String code;

    private String avatarUrl;

    private String nickName;

    private String phone;
}
