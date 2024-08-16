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
 * 门店菜单表
 * </p>
 *
 * @author nott
 * @since 2024-05-24
 */
@Getter
@Setter
@TableName("biz_menu")
public class BizMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 菜单分类id
     */
    private Long menuCatalogId;

    /**
     * 菜品名称
     */
    private String itemId;

    /**
     * 门店id
     */
    private Long shopId;

    /**
     * 删除标识
     */
    @TableLogic(delval = "1",value = "0")
    private Integer delFlag;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;


}
