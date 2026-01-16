package org.nott.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import org.nott.dto.BizMenuDTO;
import java.math.BigDecimal;
import java.util.*;

/**
* 门店菜单表 Request
*/
@Data
@ApiModel(value = "BizMenuRequest", description = "门店菜单表访问参数")
public class BizMenuRequest extends Request<BizMenuDTO> {

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