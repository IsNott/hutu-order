package org.nott.service.api;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.nott.common.utils.HutuUtils;
import org.nott.model.BizMenuCatalog;
import org.nott.service.mapper.api.BizMenuCatalogMapper;
import org.springframework.stereotype.Service;
import org.nott.vo.BizMenuCatalogVo;

import javax.annotation.Resource;
import java.util.List;

/**
* 门店菜单分类表 Service
*/
@Service
public class BizMenuCatalogService extends ServiceImpl<BizMenuCatalogMapper, BizMenuCatalog>  {

    @Resource
    private BizMenuCatalogMapper bizMenuCatalogMapper;

    public List<BizMenuCatalogVo> listByShopId(Long shopId) {
        LambdaQueryWrapper<BizMenuCatalog> wrapper = new LambdaQueryWrapper<BizMenuCatalog>()
                .eq(BizMenuCatalog::getShopId, shopId)
                .orderByAsc(BizMenuCatalog::getShowIndex);
        List<BizMenuCatalog> catalogList = bizMenuCatalogMapper.selectList(wrapper);
        List<BizMenuCatalogVo> bizMenuCatalogVos = HutuUtils.transToVos(catalogList, BizMenuCatalogVo.class);
        return bizMenuCatalogVos;
    }
}