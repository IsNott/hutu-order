package org.nott.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;


/**
 * @author Nott
 * @date 2024-7-6
 */
@Data
public class UserLoginDTO {

    @NotEmpty(message = "必填元素不能为空")
    private String phoneNumber;
}
