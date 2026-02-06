package org.nott.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author tasteTheWorld
 * @date 1月
 * version 1.0.0
 */

@ApiModel(value = "SysItemSkuSpecWithOptionVo", description = "商品SKU规格及选项页面对象")
@Data
public class SysItemSkuSpecWithOptionVo {

    @ApiModelProperty(value = "SKU规格信息")
    private SysItemSkuSpecVo skuSpec;
}
