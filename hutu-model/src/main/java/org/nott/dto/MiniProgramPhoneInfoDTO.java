package org.nott.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author Nott
 * @date 2024-7-9
 */
@Data
public class MiniProgramPhoneInfoDTO {

    @NotEmpty(message = "code不能为空")
    private String code;

    @NotEmpty(message = "encryptedData不能为空")
    private String encryptedData;

    @NotEmpty(message = "iv不能为空")
    private String iv;
}
