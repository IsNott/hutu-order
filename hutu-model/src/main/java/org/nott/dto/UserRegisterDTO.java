package org.nott.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author Nott
 * @date 2024-6-25
 */
@ApiModel("用户注册DTO")
@Data
public class UserRegisterDTO {

    private String code;

    private String avatarUrl;

    private String nickName;

    private String phone;
}
