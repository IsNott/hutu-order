package org.nott.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import org.nott.dto.BizSlideShowItemDTO;
import java.math.BigDecimal;
import java.util.*;

/**
*  Request
*/
@Data
@ApiModel(value = "BizSlideShowItemRequest", description = "访问参数")
public class BizSlideShowItemRequest extends Request<BizSlideShowItemDTO> {

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