package org.nott.service.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.nott.model.BizUserPointLog;
import org.nott.service.mapper.BizUserPointLogMapper;
import org.nott.service.service.IBizUserPointLogService;
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
public class BizUserPointLogServiceImpl extends ServiceImpl<BizUserPointLogMapper, BizUserPointLog> implements IBizUserPointLogService {

}
