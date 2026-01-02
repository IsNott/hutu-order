package org.nott.dto;

    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
* 数据字典 DTO
*/
@Data
@ApiModel(value = "SysDataDictDTO", description = "数据字典参数")
public class SysDataDictDTO {

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