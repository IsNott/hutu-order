package org.nott.service.api;


import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.nott.enums.UserPointUseEnum;
import org.nott.model.BizUserPointLog;
import org.nott.service.mapper.api.BizUserPointLogMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户积分日志表 服务实现类
 * </p>
 *
 * @author nott
 * @since 2024-08-16
 */
@Service
public class BizUserPointLogService extends ServiceImpl<BizUserPointLogMapper, BizUserPointLog>  {

     
    public void saveLog(String fee, Long usePoint, UserPointUseEnum userPointUseEnum) {
        BizUserPointLog pointLog = new BizUserPointLog();
        pointLog.setId(IdWorker.getId());
        pointLog.setPoint(usePoint);
        pointLog.setBizFee(fee);
        pointLog.setType(userPointUseEnum.getValue());
        pointLog.setDescription(userPointUseEnum.getMark());
        this.save(pointLog);
    }
}
