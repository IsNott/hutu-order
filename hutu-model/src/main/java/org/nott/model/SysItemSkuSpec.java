package org.nott.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 商品SKU规格表
 * </p>
 *
 * @author nott
 * @since 2025
 */
@Getter
@Setter
@TableName("biz_item_sku_spec")
public class SysItemSkuSpec implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品ID
     */
    private Long itemId;

    /**
     * 规格名称
     */
    private String specLabel;

    /**
     * 是否多选（0:单选，1:多选）
     */
    private Boolean multi;

    /**
     * 排序
     */
    private Integer sortOrder;

    /**
     * 是否必选
     */
    private Boolean required;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 删除标识
     */
    @TableLogic
    private Boolean delFlag;


}
