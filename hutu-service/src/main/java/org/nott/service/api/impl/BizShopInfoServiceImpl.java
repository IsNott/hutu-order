package org.nott.service.api.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.nott.common.utils.HutuUtils;
import org.nott.dto.SysShopPageDTO;
import org.nott.model.BizShopInfo;
import org.nott.service.mapper.api.BizShopInfoMapper;
import org.nott.service.api.IBizShopInfoService;
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
            vos.add(this.infoTranVoAndSetOpenStat(info));
        }
        return vos;
    }

    private ShopInfoVo infoTranVoAndSetOpenStat(BizShopInfo info){
        String startBusinessTime = info.getStartBusinessTime() + ":00";
        String endBusinessTime = info.getEndBusinessTime() + ":59";
        ShopInfoVo vo = new ShopInfoVo();
        HutuUtils.copyProperties(info, vo);
        boolean isOpen = HutuUtils.checkWeekDayAndTimeForNow(startBusinessTime, endBusinessTime, info.getWeekStartDate(), info.getWeekEndDate());
        vo.setOpen(isOpen);
        return vo;
    }

    @Override
    public ShopInfoVo getDefaultShop() {
        LambdaQueryWrapper<BizShopInfo> wrapper = new LambdaQueryWrapper<BizShopInfo>()
                .eq(BizShopInfo::getMainShop, 1);
        BizShopInfo mainShop = this.getOne(wrapper);
        ShopInfoVo shopInfoVo = HutuUtils.transToObject(mainShop, ShopInfoVo.class);
        return shopInfoVo;
    }

    @Override
    public List<ShopInfoVo> searchShopByKeyWord(String keyWord) {
        LambdaQueryWrapper<BizShopInfo> wp = new LambdaQueryWrapper<BizShopInfo>()
                .like(HutuUtils.isNotEmpty(keyWord),BizShopInfo::getAddress, keyWord)
                .or(HutuUtils.isNotEmpty(keyWord))
                .like(HutuUtils.isNotEmpty(keyWord),BizShopInfo::getShopName, keyWord);
        List<BizShopInfo> infos = this.list(wp);
        List<ShopInfoVo> shopInfoVos = new ArrayList<>();
        if(HutuUtils.isNotEmpty(infos)){
            infos.forEach(info -> shopInfoVos.add(this.infoTranVoAndSetOpenStat(info)));
        }
        return shopInfoVos;
    }

    @Override
    public ShopInfoVo getShopById(Long id) {
        BizShopInfo shopInfo = this.getById(id);
        HutuUtils.requireNotNull(shopInfo, "门店不存在");
        ShopInfoVo vo = this.infoTranVoAndSetOpenStat(shopInfo);
        return vo;
    }

    @Override
    public Page<ShopInfoVo> page(SysShopPageDTO dto, int page, int size) {
        String keyword = dto.getKeyword();
        Integer delFlag = dto.getDelFlag();

        LambdaQueryWrapper<BizShopInfo> wrapper = new LambdaQueryWrapper<>();

        wrapper.like(HutuUtils.isNotEmpty(keyword), BizShopInfo::getShopName, keyword)
               .or().like(HutuUtils.isNotEmpty(keyword), BizShopInfo::getAddress, keyword)
               .eq(delFlag != null, BizShopInfo::getDelFlag, delFlag)
               .orderByDesc(BizShopInfo::getCreateTime);

        return HutuUtils.transVOPage(this.page(new Page<>(page, size), wrapper),
                ShopInfoVo.class);
    }
}
