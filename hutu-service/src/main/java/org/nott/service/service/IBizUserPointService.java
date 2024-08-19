package org.nott.service.service;

import org.nott.model.BizUserPoint;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;

/**
 * <p>
 * 用户积分表 服务类
 * </p>
 *
 * @author nott
 * @since 2024-08-16
 */
public interface IBizUserPointService extends IService<BizUserPoint> {

    Long queryUsablePoint();

    void usePoint(String fee,Long originPoint, Long usePoint);
}
