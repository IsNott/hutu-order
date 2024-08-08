package org.nott.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Nott
 * @date 2024-8-8
 */

@Data
public class PayWayQueryDTO {

    @NotNull(message = "平台为空")
    private String platformName;
}
