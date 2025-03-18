package org.nott.service.mapper.api;

import org.nott.model.BizUserPoint;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 用户积分表 Mapper 接口
 * </p>
 *
 * @author nott
 * @since 2024-08-16
 */
public interface BizUserPointMapper extends BaseMapper<BizUserPoint> {

    int costPointByCas(Long userId,Long originPoint, Long usePoint);
}
