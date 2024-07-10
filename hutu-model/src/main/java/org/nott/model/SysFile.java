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
 * 
 * </p>
 *
 * @author nott
 * @since 2024-07-10
 */
@Getter
@Setter
@TableName("sys_file")
public class SysFile implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 文件原名
     */
    private String originName;

    /**
     * 文件存储名称
     */
    private String fileName;

    /**
     * 上传路径
     */
    private String path;

    /**
     * 业务数据id
     */
    private Long bizId;

    /**
     * 文件类型
     */
    private String type;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


}
