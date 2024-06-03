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
 * @since 2024-06-03
 */
@Getter
@Setter
@TableName("biz_sku_catalog")
public class BizSkuCatalog implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String skuCatalogName;


}
