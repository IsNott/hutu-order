package org.nott.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import org.nott.dto.SysSlideShowDTO;
import java.math.BigDecimal;
import java.util.*;

/**
*  Request
*/
@Data
@ApiModel(value = "SysSlideShowRequest", description = "访问参数")
public class SysSlideShowRequest extends Request<SysSlideShowDTO> {

    private Long id;

    @ApiModelProperty(value = "备注")
    private String mark;

    @ApiModelProperty(value = "可用开始时间")
    private Date availableStartTime;

    @ApiModelProperty(value = "可用结束时间")
    private Date availableEndTime;

    @ApiModelProperty(value = "类型 0-首页轮播 1-活动轮播 2-活动卡片")
    private Integer type;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "删除标识")
    private Boolean delFlag;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}