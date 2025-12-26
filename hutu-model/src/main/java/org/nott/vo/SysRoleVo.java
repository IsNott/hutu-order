package org.nott.vo;

    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
* 系统角色表 VO
*/
@Data
@ApiModel(value = "SysRoleVo", description = "系统角色表页面对象")
public class SysRoleVo {

    private Long id;

    @ApiModelProperty(value = "角色值")
    private String role;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "是否删除")
    private Boolean delFlag;

}