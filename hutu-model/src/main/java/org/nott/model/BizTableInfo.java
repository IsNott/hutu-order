package org.nott.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
@TableName("biz_table_info")
public class BizTableInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 桌面信息
     */
    private String tableName;


}
