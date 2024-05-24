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
@TableName("biz_item_type")
public class BizItemType implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String typeName;

    private String typeDesc;


}
