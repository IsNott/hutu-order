package org.nott.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.nott.common.utils.HutuUtils;
import org.nott.model.BizPayWay;
import org.nott.service.mapper.BizPayWayMapper;
import org.nott.service.service.IBizPayWayService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.nott.vo.PayWayVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author nott
 * @since 2024-05-24
 */
@Service
public class BizPayWayServiceImpl extends ServiceImpl<BizPayWayMapper, BizPayWay> implements IBizPayWayService {

    @Override
    public List<PayWayVo> listPayWay() {
        List<PayWayVo> vos = new ArrayList<>();
        LambdaQueryWrapper<BizPayWay> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BizPayWay::getIsUsable, 1)
                .orderByAsc(BizPayWay::getOrder);
        List<BizPayWay> payWays = this.list(wrapper);
        if (HutuUtils.isNotEmpty(payWays)) {
            for (BizPayWay payWay : payWays) {
                PayWayVo payWayVo = new PayWayVo();
                HutuUtils.copyProperties(payWay, payWayVo);
                vos.add(payWayVo);
            }
        }
        return vos;
    }
}
