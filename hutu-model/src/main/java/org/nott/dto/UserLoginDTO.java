package org.nott.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Nott
 * @date 2024-7-6
 */
@Data
public class UserLoginDTO {

    @NotNull(message = "必填元素不能为空")
    private String phoneNumber;
}
