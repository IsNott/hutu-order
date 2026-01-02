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
 * 轮播图关联内容
 * </p>
 *
 * @author nott
 * @since 2026
 */
@Getter
@Setter
@TableName("biz_slide_show_item")
public class SysSlideShowItem implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 图片地址
     */
    private String attachUrl;

    /**
     * 轮播图id
     */
    private Integer slideShowId;

    /**
     * 跳转路径
     */
    private String navigateUrl;

    /**
     * 跳转是否外链
     */
    private Boolean outside;

    /**
     * 跳转是否富文本
     */
    private Boolean richText;

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
