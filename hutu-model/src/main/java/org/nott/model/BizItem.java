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
 * 
 * </p>
 *
 * @author nott
 * @since 2024-05-24
 */
@Getter
@Setter
@TableName("biz_item")
public class BizItem implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 原始价格
     */
    private String originAmount;

    /**
     * 现价
     */
    private String actuallyAmount;

    /**
     * 商品名称
     */
    private String itemName;

    /**
     * 商品描述
     */
    private String itemDesc;

    /**
     * 商品标签
     */
    private String itemTag;

    /**
     * 特殊tag
     */
    private String special;

    /**
     * 图片地址
     */
    private String itemImgeUrls;

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
