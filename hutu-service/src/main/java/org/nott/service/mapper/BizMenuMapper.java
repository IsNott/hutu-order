package org.nott.service.mapper;

import org.apache.ibatis.annotations.Param;
import org.nott.model.BizMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.nott.vo.MenuItemVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author nott
 * @since 2024-05-24
 */
public interface BizMenuMapper extends BaseMapper<BizMenu> {

    List<MenuItemVo> getMenuItemListByCatalogId(String catalogId);

    List<MenuItemVo> getMenuItemListByShopCatalogId(@Param("shopId") Long shopId, @Param("catalogId") String catalogId);
}
