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
@TableName("biz_slide_show_item")
public class BizSlideShowItem implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 图片地址
     */
    private String attachUrl;

    private Integer sortOrder;

    private Long slideShowId;

    private String navigateUrl;

    private Boolean outside;

    private Boolean richText;

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
