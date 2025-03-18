package org.nott.service.api.impl;


import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.nott.common.exception.HutuBizException;
import org.nott.common.utils.HutuUtils;
import org.nott.enums.UserPointUseEnum;
import org.nott.model.BizUserPoint;
import org.nott.service.mapper.api.BizUserPointMapper;
import org.nott.service.api.IBizUserPointLogService;
import org.nott.service.api.IBizUserPointService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

    @Resource
    private BizUserPointMapper bizUserPointMapper;

    @Resource
    private IBizUserPointLogService bizUserPointLogService;

    @Override
    public Long queryUsablePoint() {
        long id = StpUtil.getLoginIdAsLong();
        LambdaQueryWrapper<BizUserPoint> wrapper = new LambdaQueryWrapper<BizUserPoint>().eq(BizUserPoint::getUserId, id);
        BizUserPoint point = this.getOne(wrapper);
        return HutuUtils.getIfValue(point.getPoint(), 0L);
    }

    @Override
    public void usePoint(String fee,Long originPoint, Long usePoint) {
        long id = StpUtil.getLoginIdAsLong();
        HutuUtils.requireTrue(originPoint >= usePoint,"原积分不足");
        int affectRow = bizUserPointMapper.costPointByCas(id, originPoint, usePoint);
        if(affectRow == 0){
            throw new HutuBizException("原积分已被修改，请刷新页面重试");
        }
        bizUserPointLogService.saveLog(fee,usePoint, UserPointUseEnum.USER_Active);
    }
}
