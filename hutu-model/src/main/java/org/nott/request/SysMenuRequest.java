package org.nott.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import org.nott.dto.SysMenuDTO;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.*;

/**
* 系统菜单表 Request
*/
@Data
@ApiModel(value = "SysMenuRequest", description = "系统菜单表访问参数")
public class SysMenuRequest extends Request<SysMenuDTO> {

    private Long id;

    @NotNull(message = "父菜单ID不能为空")
    @ApiModelProperty(value = "父菜单ID，0表示根菜单")
    private Long parentId;

    @ApiModelProperty(value = "菜单名称")
    private String name;

    @ApiModelProperty(value = "路由路径（对应前端路由path）")
    private String path;

    @ApiModelProperty(value = "组件路径（对应前端组件路径）")
    private String component;

    @ApiModelProperty(value = "菜单标题（对应前端meta.title）")
    private String title;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "是否显示（1显示，0隐藏）")
    private Boolean visible;

    @ApiModelProperty(value = "是否缓存（1缓存，0不缓存）")
    private Boolean keepAlive;

    @ApiModelProperty(value = "菜单类型（1菜单，2按钮）")
    private Integer type;

    @ApiModelProperty(value = "权限标识")
    private String permission;

    @ApiModelProperty(value = "删除标识")
    private Boolean delFlag;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}