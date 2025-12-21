package org.nott.dto;

    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
* 商品表 DTO
*/
@Data
@ApiModel(value = "SysProductDTO", description = "商品表参数")
public class SysProductDTO {

    private Long id;

    @ApiModelProperty(value = "商品封面")
    private String coverUrl;

    @ApiModelProperty(value = "价格")
    private String itemPrice;

    @ApiModelProperty(value = "商品名称")
    private String itemName;

    @ApiModelProperty(value = "排序")
    private Integer showIndex;

    @ApiModelProperty(value = "商品描述")
    private String itemDescription;

    @ApiModelProperty(value = "预计制作时长，单位：分")
    private Integer expectMakeTime;

    @ApiModelProperty(value = "删除标识")
    private Boolean delFlag;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}