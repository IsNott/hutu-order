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
 * @since 2026
 */
@Getter
@Setter
@TableName("biz_slide_show")
public class SysSlideShow implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 备注
     */
    private String mark;

    /**
     * 可用开始时间
     */
    private Date availableStartTime;

    /**
     * 可用结束时间
     */
    private Date availableEndTime;

    /**
     * 类型 0-首页轮播 1-活动轮播 2-活动卡片
     */
    private Integer type;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 删除标识
     */
    @TableLogic
    private Boolean delFlag;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
