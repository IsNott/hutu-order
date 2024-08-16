package org.nott.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 商品-sku关系表
 * </p>
 *
 * @author nott
 * @since 2024-06-03
 */
@Getter
@Setter
@TableName("biz_item_sku_relation")
public class BizItemSkuRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 产品id
     */
    private Long itemId;

    /**
     * 排序
     */
    private Integer displayOrder;

    /**
     * sku分类id
     */
    private Long skuCatalogId;


}
