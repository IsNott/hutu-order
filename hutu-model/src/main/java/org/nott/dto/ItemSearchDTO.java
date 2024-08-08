package org.nott.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * @author Nott
 * @date 2024-5-24
 */

@ApiModel("商品搜索-DTO")
@Setter
@Getter
public class ItemSearchDTO {

    @ApiModelProperty(value = "关键字",required = true)
    @NotEmpty(message = "搜索信息不能为空")
    private String keyWord;
}
