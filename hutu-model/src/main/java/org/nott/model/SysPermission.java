package org.nott.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 系统权限表
 * </p>
 *
 * @author nott
 * @since 2024-06-07
 */
@Getter
@Setter
@TableName("sys_permission")
public class SysPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 菜单路径
     */
    private String menuPath;

    /**
     * 访问地址
     */
    private String requestUrl;

    /**
     * 图标
     */
    private String icon;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 删除标识
     */
    private Integer delFlag;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 删除时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}
