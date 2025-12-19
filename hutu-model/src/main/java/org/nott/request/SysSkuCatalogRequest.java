package org.nott.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import org.nott.dto.SysSkuCatalogDTO;
import java.math.BigDecimal;
import java.util.*;

/**
* sku分类表 DTO
*/
@Data
@ApiModel(value = "SysSkuCatalogRequest", description = "sku分类表访问参数")
public class SysSkuCatalogRequest extends Request<SysSkuCatalogDTO> {

    private Long id;

    @ApiModelProperty(value = "sku分类名称")
    private String skuCatalogName;

    @ApiModelProperty(value = "关联门店id")
    private Long shopId;

    @ApiModelProperty(value = "排序")
    private Integer showIndex;

    @ApiModelProperty(value = "点单页显示")
    private Integer showSide;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "删除标识")
    private Boolean delFlag;

}