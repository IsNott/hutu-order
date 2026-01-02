package org.nott.dto;

    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
* 门店菜单分类表 DTO
*/
@Data
@ApiModel(value = "SysMenuCatalogDTO", description = "门店菜单分类表参数")
public class SysMenuCatalogDTO {

    private Long id;

    @ApiModelProperty(value = "菜单分类名称")
    private String menuCatalogName;

    @ApiModelProperty(value = "关联门店id")
    private Long shopId;

    @ApiModelProperty(value = "排序")
    private Integer showIndex;

    @ApiModelProperty(value = "点单页显示")
    private Boolean showSide;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "删除标识")
    private Boolean delFlag;

}