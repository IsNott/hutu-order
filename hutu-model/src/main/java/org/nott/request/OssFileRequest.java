package org.nott.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.nott.dto.OssFileDTO;
import org.nott.model.OssFile;

/**
 * @Author tasteTheWorld
 * @date 12月
 * version 1.0.0
 */

@Data
@ApiModel("Oss文件信息请求参数")
public class OssFileRequest extends Request<OssFileDTO>{

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
}
