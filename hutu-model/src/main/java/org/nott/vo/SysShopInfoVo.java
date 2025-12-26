package org.nott.vo;

    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
    import java.math.BigDecimal;
    import java.util.Date;

/**
* 门店信息表 VO
*/
@Data
@ApiModel(value = "SysShopInfoVo", description = "门店信息表页面对象")
public class SysShopInfoVo {

    private Long id;

    @ApiModelProperty(value = "名称")
    private String shopName;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "联系电话")
    private String phone;

    @ApiModelProperty(value = "营业时间")
    private String startBusinessTime;

    @ApiModelProperty(value = "打烊时间")
    private String endBusinessTime;

    @ApiModelProperty(value = "是否店休")
    private Integer closeNow;

    @ApiModelProperty(value = "经度")
    private BigDecimal longitude;

    @ApiModelProperty(value = "纬度")
    private BigDecimal latitude;

    @ApiModelProperty(value = "每周营业开始日")
    private Integer weekStartDate;

    @ApiModelProperty(value = "每周营业结束日")
    private Integer weekEndDate;

    @ApiModelProperty(value = "是否主店")
    private Integer mainShop;

    private Date createTime;

    private Date updateTime;

    private Boolean delFlag;

}