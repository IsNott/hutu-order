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
 * @since 2025-04-15
 */
@Getter
@Setter
@TableName("biz_business_config")
public class BizBusinessConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 业务key
     */
    private String bizKey;

    /**
     * 业务正文
     */
    private String bizContext;


}
