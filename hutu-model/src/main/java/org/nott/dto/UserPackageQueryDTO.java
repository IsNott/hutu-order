package org.nott.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Nott
 * @date 2024-6-3
 */

@Data
public class UserPackageQueryDTO {

    @NotNull(message = "用户id不能为空")
    private Long userId;
}
