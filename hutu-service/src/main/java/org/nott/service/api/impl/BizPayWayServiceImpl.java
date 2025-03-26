package org.nott.service.api.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.nott.common.utils.HutuUtils;
import org.nott.dto.PayWayQueryDTO;
import org.nott.model.BizPayWay;
import org.nott.service.mapper.api.BizPayWayMapper;
import org.nott.service.api.IBizPayWayService;
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
    public List<PayWayVo> listPayWay(PayWayQueryDTO dto) {
        List<PayWayVo> vos = new ArrayList<>();

        LambdaQueryWrapper<BizPayWay> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(BizPayWay::getIsUsable, 1)
                .eq(BizPayWay::getSupportPlatform,dto.getPlatformName())
                .or().eq(BizPayWay::getSupportPlatform,"ALL")
                .orderByAsc(BizPayWay::getDisplayOrder);

        List<BizPayWay> payWays = this.list(wrapper);
        if (HutuUtils.isNotEmpty(payWays)) {
           payWays.forEach(r -> vos.add(HutuUtils.transToObject(r,PayWayVo.class)));
        }
        return vos;
    }
}
