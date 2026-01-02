package org.nott.service.api;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.nott.model.BizShopInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.nott.request.BizShopInfoRequest;
import org.nott.service.mapper.api.BizShopInfoMapper;
import org.nott.vo.ShopInfoVo;
import org.springframework.stereotype.Service;
import org.nott.dto.BizShopInfoDTO;
import org.nott.vo.BizShopInfoVo;
import org.nott.common.utils.HutuUtils;
import org.nott.common.exception.HutuBizException;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
* 门店信息表 Service
*/
@Service
public class BizShopInfoService extends ServiceImpl<BizShopInfoMapper, BizShopInfo>  {

    @Resource
    private BizShopInfoMapper bizShopInfoMapper;

    public IPage<BizShopInfoVo> queryPage(Integer page, Integer size, BizShopInfoDTO dto) {
        MPJLambdaWrapper<BizShopInfo> wrapper = new MPJLambdaWrapper<BizShopInfo>()
            .selectAll(BizShopInfo.class)
            .orderByDesc(BizShopInfo::getCreateTime);
        return bizShopInfoMapper.selectJoinPage(new Page<>(page, size), BizShopInfoVo.class, wrapper);
    }


    public BizShopInfoVo save(BizShopInfoDTO dto) {
        BizShopInfo entity = HutuUtils.transToObject(dto, BizShopInfo.class);
        entity.setDelFlag(false);
        this.save(entity);
        return HutuUtils.transToObject(entity, BizShopInfoVo.class);
    }

    public BizShopInfoVo update(BizShopInfoDTO dto) {
        Long id = dto.getId();
        BizShopInfo entity = this.getById(id);
        if(HutuUtils.isEmpty(entity)){
            throw new HutuBizException("Entity not found.");
        }
        HutuUtils.copyProperties(dto, entity);
        this.updateById(entity);
        return HutuUtils.transToObject(entity, BizShopInfoVo.class);
    }

    public List<ShopInfoVo> businessShops(BizShopInfoRequest request) {
        String shopName = request.getShopName();
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (day == 0){
            day = 7;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        MPJLambdaWrapper<BizShopInfo> wrapper = new MPJLambdaWrapper<BizShopInfo>()
            .selectAll(BizShopInfo.class)
            .like(HutuUtils.isNotEmpty(shopName), BizShopInfo::getShopName, shopName)
                .apply("TIME(CONCAT(start_business_time, ':00')) <= {0}", simpleDateFormat.format(date))
                .apply("TIME(CONCAT(end_business_time, ':00')) >= {0}", simpleDateFormat.format(date))
                .ne(BizShopInfo::getCloseNow, 1)
            .orderByDesc(BizShopInfo::getCreateTime);
        List<BizShopInfo> bizShopInfos = bizShopInfoMapper.selectList(wrapper);
        return HutuUtils.transToVos(bizShopInfos, ShopInfoVo.class);
    }
}