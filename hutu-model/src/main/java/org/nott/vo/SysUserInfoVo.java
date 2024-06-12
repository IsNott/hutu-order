package org.nott.vo;

import lombok.Data;

import java.util.List;

/**
 * @author Nott
 * @date 2024-6-12
 */

@Data
public class SysUserInfoVo {

    private List<String> roles;

    private String name;

    private String avatar;

    private String introduction;

}
