package org.nott.vo;

    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
* 轮播图关联内容 VO
*/
@Data
@ApiModel(value = "SysSlideShowItemVo", description = "轮播图关联内容页面对象")
public class SysSlideShowItemVo {

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