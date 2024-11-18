package org.nott.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Nott
 * @date 2024-6-3
 */

@Data
@ApiModel("用户购物袋查询对象")
public class UserPackageQueryDTO {

    @NotNull(message = "用户id不能为空")
    private Long userId;
}
