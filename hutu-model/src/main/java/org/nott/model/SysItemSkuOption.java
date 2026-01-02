package org.nott.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * SKU规格选项表
 * </p>
 *
 * @author nott
 * @since 2025
 */
@Getter
@Setter
@TableName("biz_item_sku_option")
public class SysItemSkuOption implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;

    /**
     * 规格ID
     */
    private Long specId;

    /**
     * 选项名称
     */
    private String optionLabel;

    /**
     * 选项code
     */
    private String optionCode;

    /**
     * 父级ID
     */
    private String parentId;

    /**
     * 附加价格
     */
    private BigDecimal additionalPrice;

    /**
     * 目前是否禁用
     */
    private Boolean nowDisabled;

    /**
     * 排序
     */
    private Integer sortOrder;

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
