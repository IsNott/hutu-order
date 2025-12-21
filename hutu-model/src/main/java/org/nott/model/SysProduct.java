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
 * 商品表
 * </p>
 *
 * @author nott
 * @since 2025
 */
@Getter
@Setter
@TableName("biz_product")
public class SysProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 商品封面
     */
    private String coverUrl;

    /**
     * 价格
     */
    private String itemPrice;

    /**
     * 商品名称
     */
    private String itemName;

    /**
     * 排序
     */
    private Integer showIndex;

    /**
     * 商品描述
     */
    private String itemDescription;

    /**
     * 预计制作时长，单位：分
     */
    private Integer expectMakeTime;

    /**
     * 删除标识
     */
    @TableLogic
    private Boolean delFlag;

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


}
