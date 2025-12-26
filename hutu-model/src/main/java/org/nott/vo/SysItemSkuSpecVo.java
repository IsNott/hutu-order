package org.nott.vo;

    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
* 商品SKU规格表 VO
*/
@Data
@ApiModel(value = "SysItemSkuSpecVo", description = "商品SKU规格表页面对象")
public class SysItemSkuSpecVo {

    private Long id;

    @ApiModelProperty(value = "商品ID")
    private Long itemId;

    @ApiModelProperty(value = "规格名称")
    private String specLabel;

    @ApiModelProperty(value = "是否多选（0:单选，1:多选）")
    private Boolean multi;

    @ApiModelProperty(value = "排序")
    private Integer sortOrder;

    @ApiModelProperty(value = "是否必选")
    private Boolean required;

    private Date createTime;

    private Date updateTime;

    @ApiModelProperty(value = "删除标识")
    private Boolean delFlag;

}