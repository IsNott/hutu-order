package org.nott.dto;

    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
*  DTO
*/
@Data
@ApiModel(value = "BizSlideShowItemDTO", description = "参数")
public class BizSlideShowItemDTO {

    private Long id;

    @ApiModelProperty(value = "图片地址")
    private String attachUrl;

    private Integer sortOrder;

    private Long slideShowId;

    private String navigateUrl;

    private Boolean outside;

    private Boolean richText;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "删除标识")
    private Boolean delFlag;

}