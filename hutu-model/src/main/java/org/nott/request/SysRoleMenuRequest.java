package org.nott.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import org.nott.dto.SysRoleMenuDTO;
import java.math.BigDecimal;
import java.util.*;

/**
* 角色-菜单权限表 Request
*/
@Data
@ApiModel(value = "SysRoleMenuRequest", description = "角色-菜单权限表访问参数")
public class SysRoleMenuRequest extends Request<SysRoleMenuDTO> {

    private Long id;

    @ApiModelProperty(value = "角色ID")
    private Long roleId;

    @ApiModelProperty(value = "菜单ID")
    private Long menuId;

    @ApiModelProperty(value = "删除标识")
    private Boolean delFlag;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}