package org.nott.service.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.nott.model.BizUserPoint;
import org.nott.service.mapper.BizUserPointMapper;
import org.nott.service.service.IBizUserPointService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户积分表 服务实现类
 * </p>
 *
 * @author nott
 * @since 2024-08-16
 */
@Service
public class BizUserPointServiceImpl extends ServiceImpl<BizUserPointMapper, BizUserPoint> implements IBizUserPointService {

}
