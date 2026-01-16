package org.nott.service.api;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.nott.model.BizMenu;
import org.nott.model.BizMenuCatalog;
import org.nott.model.BizProduct;
import org.nott.service.mapper.api.BizMenuMapper;
import org.springframework.stereotype.Service;
import org.nott.vo.BizMenuVo;

import javax.annotation.Resource;
import java.util.List;

/**
* 门店菜单表 Service
*/
@Service
public class BizMenuService extends ServiceImpl<BizMenuMapper, BizMenu>  {

    @Resource
    private BizMenuMapper bizMenuMapper;

    public List<BizMenuVo> listByShopId(Long shopId) {
        MPJLambdaWrapper<BizMenu> wrapper = new MPJLambdaWrapper<BizMenu>()
                .selectAll(BizMenu.class)
                .select(BizMenuCatalog::getMenuCatalogName)
                .select(BizProduct::getItemName, BizProduct::getItemDescription, BizProduct::getItemPrice, BizProduct::getCoverUrl, BizProduct::getShowIndex)
                .leftJoin(BizMenuCatalog.class, BizMenuCatalog::getId, BizMenu::getMenuCatalogId)
                .leftJoin(BizProduct.class, BizProduct::getId, BizMenu::getItemId)
                .eq(BizMenu::getShopId, shopId)
                .orderByAsc(BizProduct::getShowIndex);;
        List<BizMenuVo> menus = bizMenuMapper.selectJoinList(BizMenuVo.class, wrapper);
        return menus;
    }
}