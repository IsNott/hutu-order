package org.nott.service.api;

import org.nott.enums.UserPointUseEnum;
import org.nott.model.BizUserPointLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户积分日志表 服务类
 * </p>
 *
 * @author nott
 * @since 2024-08-16
 */
public interface IBizUserPointLogService extends IService<BizUserPointLog> {

    void saveLog(String fee, Long usePoint, UserPointUseEnum userPointUseEnum);
}
