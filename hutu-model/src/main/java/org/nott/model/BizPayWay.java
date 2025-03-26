package org.nott.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 支付方式表
 * </p>
 *
 * @author nott
 * @since 2024-05-24
 */
@Getter
@Setter
@TableName("biz_pay_way")
public class BizPayWay implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 支付编码
     */
    private String payCode;

    /**
     * 交易地址
     */
    private String payUrl;

    /**
     * 退款地址
     */
    private String refundUrl;

    /**
     * 查询地址
     */
    private String queryUrl;

    /**
     * 交易系统名称
     */
    private String paymentName;

    /**
     * 展示排序
     */
    private Integer displayOrder;
    /**
     * 图标地址
     */
    private String icon;
    /**
     * 展示收起 0-不收起 1-收起
     */
    private Integer packUp;
    /**
     * 是否在用
     */
    private Integer isUsable;
    /**
     * 支持的平台（枚举）
     */
    private String supportPlatform;


}
