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
@TableName("biz_pay_way")
public class BizPayWay implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

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


}
