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
 * 数据字典
 * </p>
 *
 * @author nott
 * @since 2025
 */
@Getter
@Setter
@TableName("sys_data_dict")
public class SysDataDict implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 父级id
     */
    private Long parentId;

    /**
     * 排序
     */
    private Integer index;

    /**
     * 标题
     */
    private String label;

    /**
     * 值
     */
    private String value;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableLogic
    private Boolean delFlag;


}
