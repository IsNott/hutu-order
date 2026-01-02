package org.nott.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import org.nott.dto.SysItemSkuOptionDTO;
import java.math.BigDecimal;
import java.util.*;

/**
* SKU规格选项表 Request
*/
@Data
@ApiModel(value = "SysItemSkuOptionRequest", description = "SKU规格选项表访问参数")
public class SysItemSkuOptionRequest extends Request<SysItemSkuOptionDTO> {

    private Long id;

    @ApiModelProperty(value = "规格ID")
    private Long specId;

    @ApiModelProperty(value = "选项名称")
    private String optionLabel;

    @ApiModelProperty(value = "选项code")
    private String optionCode;

    @ApiModelProperty(value = "父级ID")
    private String parentId;

    @ApiModelProperty(value = "附加价格")
    private BigDecimal additionalPrice;

    @ApiModelProperty(value = "目前是否禁用")
    private Boolean nowDisabled;

    @ApiModelProperty(value = "排序")
    private Integer sortOrder;

    private Date createTime;

    private Date updateTime;

    @ApiModelProperty(value = "删除标识")
    private Boolean delFlag;

}