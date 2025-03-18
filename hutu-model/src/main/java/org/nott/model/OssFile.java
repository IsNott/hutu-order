package org.nott.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Oss文件
 * @author Nott
 * @date 2025-3-18
 */

@TableName("oss_file")
@Data
public class OssFile implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 业务id
     */
    private Long bizId;

    /**
     * 存储名称
     */
    private String fileName;

    /**
     * 原始名称
     */
    private String originName;

    /**
     * 访问路径
     */
    private String path;

    /**
     * 文件后缀
     */
    private String prefix;

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
     * 删除标识
     */
    private Integer delFlag;

}
