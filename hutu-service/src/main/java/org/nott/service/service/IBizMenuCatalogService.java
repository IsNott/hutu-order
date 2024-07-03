package org.nott.service.service;

import org.nott.model.BizMenuCatalog;
import com.baomidou.mybatisplus.extension.service.IService;
import org.nott.vo.MenuCatalogVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author nott
 * @since 2024-05-24
 */
public interface IBizMenuCatalogService extends IService<BizMenuCatalog> {

    List<MenuCatalogVo> getCatalogByShopId(Long shopId);
}
