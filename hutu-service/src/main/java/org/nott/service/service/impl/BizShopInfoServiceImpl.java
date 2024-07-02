package org.nott.service.service.impl;

import org.nott.common.utils.HutuUtils;
import org.nott.model.BizShopInfo;
import org.nott.service.mapper.BizShopInfoMapper;
import org.nott.service.service.IBizShopInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.nott.vo.ShopInfoVo;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author nott
 * @since 2024-06-26
 */
@Service
public class BizShopInfoServiceImpl extends ServiceImpl<BizShopInfoMapper, BizShopInfo> implements IBizShopInfoService {

    @Override
    public List<ShopInfoVo> listShopInfo() {
        List<BizShopInfo> shopInfos = this.list();
        ArrayList<ShopInfoVo> vos = new ArrayList<>();
        for (BizShopInfo info : shopInfos) {
            String startBusinessTime = info.getStartBusinessTime() + ":00";
            String endBusinessTime = info.getEndBusinessTime() + ":59";
            ShopInfoVo vo = new ShopInfoVo();
            HutuUtils.copyProperties(info, vo);
            boolean isOpen = HutuUtils.checkWeekDayAndTimeForNow(startBusinessTime, endBusinessTime, info.getWeekStartDate(), info.getWeekEndDate());
            vo.setOpen(isOpen);
            vos.add(vo);
        }
        return vos;
    }
}
