package org.nott.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import org.nott.dto.SysUserDTO;
import java.math.BigDecimal;
import java.util.*;

/**
* 系统用户表 Request
*/
@Data
@ApiModel(value = "SysUserRequest", description = "系统用户表访问参数")
public class SysUserRequest extends Request<SysUserDTO> {

    private Long id;

    @ApiModelProperty(value = "员工姓名")
    private String realName;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "联系电话")
    private String phone;

    @ApiModelProperty(value = "头像")
    private String avatarUrl;

    @ApiModelProperty(value = "是否冻结")
    private Boolean isLock;

    @ApiModelProperty(value = "删除标识")
    private Boolean delFlag;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}