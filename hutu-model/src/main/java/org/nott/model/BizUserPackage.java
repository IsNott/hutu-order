package org.nott.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户购物袋表
 * </p>
 *
 * @author nott
 * @since 2024-06-03
 */
@Getter
@Setter
@TableName("biz_user_package")
public class BizUserPackage implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 产品id
     */
    private Long itemId;

    /**
     * 选购件数
     */
    private Integer itemPiece;

    /**
     * 选购规格，以逗号分隔
     */
    private String skuItemContents;


}
