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
 * 用户优惠券表
 * </p>
 *
 * @author nott
 * @since 2024-08-16
 */
@Getter
@Setter
@TableName("biz_coupon")
public class BizCoupon implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 条件id
     */
    private Long conditionId;

    /**
     * 优惠券标题
     */
    private String titile;

    /**
     * 二级标题
     */
    private String subTitle;

    /**
     * 创建人id
     */
    private Long creatorId;

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
     * 更新时间
     */
    private LocalDateTime updateTime;


}
