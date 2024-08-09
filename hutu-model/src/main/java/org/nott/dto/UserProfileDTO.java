package org.nott.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Nott
 * @date 2024-7-9
 */
@ApiModel("用户个人信息DTO")
@Data
public class UserProfileDTO {

    @ApiModelProperty("头像地址")
    private String avatarUrl;

    @ApiModelProperty("性别 0-女 1-男")
    private Integer gender;

    @ApiModelProperty("昵称")
    private String nickName;
}
