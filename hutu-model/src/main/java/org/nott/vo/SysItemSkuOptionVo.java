package org.nott.vo;

    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
* SKU规格选项表 VO
*/
@Data
@ApiModel(value = "SysItemSkuOptionVo", description = "SKU规格选项表页面对象")
public class SysItemSkuOptionVo {

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