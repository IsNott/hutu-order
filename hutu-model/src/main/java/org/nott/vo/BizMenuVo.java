package org.nott.vo;

    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
* 门店菜单表 VO
*/
@Data
@ApiModel(value = "BizMenuVo", description = "门店菜单表页面对象")
public class BizMenuVo {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "菜单分类id")
    private Long menuCatalogId;

    @ApiModelProperty(value = "菜单分类名称")
    private String menuCatalogName;

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

    @ApiModelProperty(value = "单价")
    private String itemPrice;

    @ApiModelProperty(value = "商品封面")
    private String coverUrl;

    @ApiModelProperty(value = "商品名称")
    private String itemName;

    @ApiModelProperty(value = "排序")
    private Integer showIndex;

    @ApiModelProperty(value = "商品描述")
    private String itemDescription;

    @ApiModelProperty(value = "预计制作时长，单位：分")
    private Integer expectMakeTime;

}