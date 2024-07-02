package org.nott.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author nott
 * @since 2024-06-26
 */
@Getter
@Setter
@TableName("biz_shop_info")
public class BizShopInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 名称
     */
    private String shopName;

    /**
     * 地址
     */
    private String address;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 营业时间
     */
    private String startBusinessTime;

    /**
     * 打烊时间
     */
    private String endBusinessTime;

    /**
     * 是否店休
     */
    private Integer closeNow;

    /**
     * 经度
     */
    private BigDecimal longitude;

    /**
     * 纬度
     */
    private BigDecimal latitude;

    /**
     * 每周营业开始日
     */
    private Integer weekStartDate;

    /**
     * 每周营业结束日
     */
    private Integer weekEndDate;


}
