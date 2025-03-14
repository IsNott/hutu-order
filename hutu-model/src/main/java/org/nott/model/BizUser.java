package org.nott.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author nott
 * @since 2024-05-24
 */
@Getter
@Setter
@TableName("biz_user")
public class BizUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 微信/支付宝 openid
     */
    private String openId;

    /**
     * 头像地址
     */
    private String avatarUrl;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 登录次数
     */
    private Integer loginCount;

    /**
     * 注册时间
     */
    private Date registTime;

    /**
     * 最后登录时间
     */
    private Date lastLogTime;

    /**
     * 实际余额
     */
    private BigDecimal actualBalance;

    /**
     * 赠送余额
     */
    private BigDecimal giftBalance;


}
