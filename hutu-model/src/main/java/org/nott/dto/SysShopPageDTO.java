package org.nott.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("后台门店查询-DTO")
@Data
public class SysShopPageDTO {

    private String keyword; // 搜索关键字

    private Integer delFlag; // 门店状态 0-正常 1-停用
}
