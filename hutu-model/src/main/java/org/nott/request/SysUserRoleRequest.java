package org.nott.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import org.nott.dto.SysUserRoleDTO;
import java.math.BigDecimal;
import java.util.*;

/**
* 系统用户-角色关系表 Request
*/
@Data
@ApiModel(value = "SysUserRoleRequest", description = "系统用户-角色关系表访问参数")
public class SysUserRoleRequest extends Request<SysUserRoleDTO> {

    private Long id;

    @ApiModelProperty(value = "系统用户id")
    private Long userId;

    @ApiModelProperty(value = "角色id")
    private Long roleId;

    @ApiModelProperty(value = "删除标识")
    private Boolean delFlag;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}