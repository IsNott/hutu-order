package org.nott.dto;

    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
* 系统用户-角色关系表 DTO
*/
@Data
@ApiModel(value = "SysUserRoleDTO", description = "系统用户-角色关系表参数")
public class SysUserRoleDTO {

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