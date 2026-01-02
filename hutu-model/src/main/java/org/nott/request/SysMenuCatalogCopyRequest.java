package org.nott.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.nott.dto.SysMenuCatalogCopyDTO;

import java.util.List;

/**
 * @Author tasteTheWorld
 * @date 12月
 * version 1.0.0
 */

@Data
@ApiModel(value = "SysMenuCatalogCopyRequest", description = "门店菜单分类复制请求对象")
public class SysMenuCatalogCopyRequest extends Request<SysMenuCatalogCopyDTO> {

    @ApiModelProperty(value = "目标门店ID集合")
    private List<Long> shopIds;

    @ApiModelProperty(value = "菜单分类ID集合")
    private List<Long> menuCatalogIds;

}
