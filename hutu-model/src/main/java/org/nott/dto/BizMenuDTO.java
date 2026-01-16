package org.nott.dto;

    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
* 门店菜单表 DTO
*/
@Data
@ApiModel(value = "BizMenuDTO", description = "门店菜单表参数")
public class BizMenuDTO {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "菜单分类id")
    private Long menuCatalogId;

    @ApiModelProperty(value = "菜品名称")
    private String itemId;

    @ApiModelProperty(value = "门店id")
    private Long shopId;

    @ApiModelProperty(value = "删除标识")
    private Integer delFlag;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}