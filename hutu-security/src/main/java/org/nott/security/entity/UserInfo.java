package org.nott.security.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Nott
 * @date 2025-12-01
 */
@Data
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;

    /**
     * 员工姓名
     */
    private String realName;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 头像
     */
    private String avatarUrl;


    private Integer isLock;


}
