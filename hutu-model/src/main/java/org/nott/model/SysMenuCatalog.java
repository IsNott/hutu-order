package org.nott.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 门店菜单分类表
 * </p>
 *
 * @author nott
 * @since 2025
 */
@Getter
@Setter
@TableName("biz_menu_catalog")
public class SysMenuCatalog implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 菜单分类名称
     */
    private String menuCatalogName;

    /**
     * 关联门店id
     */
    private Long shopId;

    /**
     * 排序
     */
    private Integer showIndex;

    /**
     * 点单页显示
     */
    private Boolean showSide;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 删除标识
     */
    @TableLogic
    private Boolean delFlag;


}
