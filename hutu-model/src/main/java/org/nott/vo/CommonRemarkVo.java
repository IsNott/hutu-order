package org.nott.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Nott
 * @date 2025-3-24
 */

@Data
@ApiModel("常用备注Vo")
public class CommonRemarkVo {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("内容")
    private String context;

    @ApiModelProperty("删除标识")
    private Integer delFlag;
}
