package org.nott.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * @author Nott
 * @date 2024-5-24
 */

@Setter
@Getter
public class ItemSearchDTO {

    @NotEmpty(message = "搜索信息不能为空")
    private String keyWord;
}
