package org.nott.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 系统用户-角色关系表
 * </p>
 *
 * @author nott
 * @since 2024-06-07
 */
@Getter
@Setter
@TableName("sys_user_role")
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 系统用户id
     */
    private Long userId;

    /**
     * 角色id
     */
    private Long roleId;


}
