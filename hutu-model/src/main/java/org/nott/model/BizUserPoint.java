package org.nott.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户积分表
 * </p>
 *
 * @author nott
 * @since 2024-08-16
 */
@Getter
@Setter
@TableName("biz_user_point")
public class BizUserPoint implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 积分
     */
    private Long point;


}
