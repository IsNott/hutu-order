package org.nott.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import org.nott.dto.SysDataDictDTO;
import java.math.BigDecimal;
import java.util.*;

/**
* 数据字典 Request
*/
@Data
@ApiModel(value = "SysDataDictRequest", description = "数据字典访问参数")
public class SysDataDictRequest extends Request<SysDataDictDTO> {

    private Long id;

    @ApiModelProperty(value = "父级id")
    private Long parentId;

    @ApiModelProperty(value = "排序")
    private Integer index;

    @ApiModelProperty(value = "标题")
    private String label;

    @ApiModelProperty(value = "值")
    private String value;

    private Date createTime;

    private Date updateTime;

    private Boolean delFlag;

}