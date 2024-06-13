package org.nott.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author nott
 * @since 2024-06-13
 */
@Getter
@Setter
@TableName("biz_comment")
public class BizComment implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 评价打分
     */
    private String commentRate;

    /**
     * 评价正文
     */
    private String commentContext;

    /**
     * 评价图片
     */
    private String commentUrls;

    /**
     * 评价用户id
     */
    private Long commentUserId;


}
