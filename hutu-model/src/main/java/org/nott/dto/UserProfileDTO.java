package org.nott.dto;

import lombok.Data;

/**
 * @author Nott
 * @date 2024-7-9
 */

@Data
public class UserProfileDTO {

    private String avatarUrl;

    private Integer gender;

    private String nickName;
}
