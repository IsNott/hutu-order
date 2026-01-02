package org.nott.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author tasteTheWorld
 * @date 12月
 * version 1.0.0
 */
@Data
@ApiModel(value = "SysMenuTreeNodeVo", description = "系统菜单树节点对象")
public class SysMenuTreeNodeVo extends SysMenuVo {

    @ApiModelProperty(value = "是否有子节点")
    private boolean hasChildren;

    @ApiModelProperty(value = "子节点列表")
    private List<SysMenuTreeNodeVo> children;

    public boolean isHasChildren() {
        return children != null && !children.isEmpty();
    }

    public void setHasChildren() {
        this.hasChildren = children != null && !children.isEmpty();
    }
}
