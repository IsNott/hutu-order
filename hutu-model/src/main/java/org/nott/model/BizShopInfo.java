package org.nott.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 门店信息表
 * </p>
 *
 * @author nott
 * @since 2026
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

    /**
     * 是否主店
     */
    private Integer mainShop;

    /**
     * 封面图
     */
    private String coverUrl;

    /**
     * 轮播图
     */
    private String swipeImage;

    /**
     * 点单页面显示样式
     */
    private String pageStyle;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 删除标识
     */
    @TableLogic
    private Boolean delFlag;


}
