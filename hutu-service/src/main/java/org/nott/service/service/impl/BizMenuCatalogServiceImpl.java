package org.nott.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.nott.common.utils.HutuUtils;
import org.nott.model.BizMenuCatalog;
import org.nott.service.mapper.BizMenuCatalogMapper;
import org.nott.service.service.IBizMenuCatalogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.nott.vo.MenuCatalogVo;
import org.nott.vo.MenuItemVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author nott
 * @since 2024-05-24
 */
@Service
public class BizMenuCatalogServiceImpl extends ServiceImpl<BizMenuCatalogMapper, BizMenuCatalog> implements IBizMenuCatalogService {

    @Override
    public List<MenuCatalogVo> getCatalogByShopId(Long shopId) {
        LambdaQueryWrapper<BizMenuCatalog> wrapper = new LambdaQueryWrapper<BizMenuCatalog>()
                .eq(BizMenuCatalog::getShopId, shopId)
                .eq(BizMenuCatalog::getDelFlag, 0);
        List<BizMenuCatalog> bizMenuCatalogs = this.list(wrapper);
        List<MenuCatalogVo> vos = HutuUtils.transToVos(bizMenuCatalogs, MenuCatalogVo.class);
        return vos;
    }
}
