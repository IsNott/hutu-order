package org.nott.service.mapper.api;

import org.nott.model.BizUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author nott
 * @since 2024-05-24
 */
public interface BizUserMapper extends BaseMapper<BizUser> {

    int costPointByCas(Long userId,Long originPoint, Long usePoint);

}
