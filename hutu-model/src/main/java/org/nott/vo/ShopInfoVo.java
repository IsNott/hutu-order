package org.nott.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.nott.model.BizShopInfo;

import java.util.List;

/**
 * @author Nott
 * @date 2024-7-2
 */

@Data
@ApiModel("门店信息")
public class ShopInfoVo extends BizShopInfo {

    @ApiModelProperty(value = "封面图")
    private String coverUrl;

    @ApiModelProperty(value = "轮播图")
    private List<String> swipeImage;

    @ApiModelProperty(value = "是否营业", example = "true")
    private boolean isOpen;
}
