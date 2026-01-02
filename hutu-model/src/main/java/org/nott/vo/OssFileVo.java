package org.nott.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Nott
 * @date 2025-3-18
 */
@Data
@ApiModel("Oss文件信息")
public class OssFileVo {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("业务id")
    private Long bizId;

    @ApiModelProperty("存储名称")
    private String fileName;

    @ApiModelProperty("原始名称")
    private String originName;

    @ApiModelProperty("排序")
    private Integer sortOrder;

    @ApiModelProperty("访问路径")
    private String url;

    @ApiModelProperty("文件后缀")
    private String prefix;

    @ApiModelProperty("创建时间")
    private String createTime;

    @ApiModelProperty("更新时间")
    private String updateTime;
}
