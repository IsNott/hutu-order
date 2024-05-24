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
@TableName("biz_package")
public class BizPackage implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 包含菜品id
     */
    private String items;

    /**
     * 套餐名称
     */
    private String packageName;

    /**
     * 套餐原价
     */
    private String packageOriginAmount;

    /**
     * 套餐现价
     */
    private String packageActuallyAmount;

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

    /**
     * 过期时间
     */
    private Date expireTime;


}
