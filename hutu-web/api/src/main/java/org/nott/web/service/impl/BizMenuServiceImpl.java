package org.nott.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.nott.model.BizMenu;
import org.nott.vo.MenuItemVo;
import org.nott.web.mapper.BizMenuMapper;
import org.nott.web.service.IBizMenuService;
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

    @Override
    public List<MenuItemVo> getByCatalogId(String catalogId) {
        List<MenuItemVo> itemVoList = bizMenuMapper.getMenuItemListByCatalogId(catalogId);
        return itemVoList;
    }
}
