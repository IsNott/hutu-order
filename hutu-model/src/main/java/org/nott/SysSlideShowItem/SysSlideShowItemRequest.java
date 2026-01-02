package org.nott.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import org.nott.dto.SysSlideShowItemDTO;
import java.math.BigDecimal;
import java.util.*;

/**
* 轮播图关联内容 Request
*/
@Data
@ApiModel(value = "SysSlideShowItemRequest", description = "轮播图关联内容访问参数")
public class SysSlideShowItemRequest extends Request<SysSlideShowItemDTO> {

    private Long id;

    @ApiModelProperty(value = "图片地址")
    private String attachUrl;

    @ApiModelProperty(value = "轮播图id")
    private Integer slideShowId;

    @ApiModelProperty(value = "跳转路径")
    private String navigateUrl;

    @ApiModelProperty(value = "跳转是否外链")
    private Boolean outside;

    @ApiModelProperty(value = "跳转是否富文本")
    private Boolean richText;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "删除标识")
    private Boolean delFlag;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}