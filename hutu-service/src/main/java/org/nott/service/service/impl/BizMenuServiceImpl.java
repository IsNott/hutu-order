package org.nott.service.service.impl;

import org.nott.model.BizMenu;
import org.nott.service.service.ISysFileService;
import org.nott.vo.MenuItemVo;
import org.nott.service.mapper.BizMenuMapper;
import org.nott.service.service.IBizMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class BizMenuServiceImpl extends ServiceImpl<BizMenuMapper, BizMenu> implements IBizMenuService {

    @Resource
    private BizMenuMapper bizMenuMapper;
    @Resource
    private ISysFileService sysFileService;

    @Override
    public List<MenuItemVo> getByCatalogId(String catalogId) {
        List<MenuItemVo> itemVoList = bizMenuMapper.getMenuItemListByCatalogId(catalogId);
        return itemVoList;
    }

    @Override
    public List<MenuItemVo> getByShopCatalogId(Long shopId,String catalogId) {
        List<MenuItemVo> itemVoList = bizMenuMapper.getMenuItemListByShopCatalogId(shopId,catalogId);
        return itemVoList;
    }
}
