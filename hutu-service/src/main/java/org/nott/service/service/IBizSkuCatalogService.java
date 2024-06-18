package org.nott.service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.nott.dto.SkuCatalogSearchDTO;
import org.nott.model.BizSkuCatalog;
import com.baomidou.mybatisplus.extension.service.IService;
import org.nott.vo.SkuCatalogItemVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author nott
 * @since 2024-06-03
 */
public interface IBizSkuCatalogService extends IService<BizSkuCatalog> {

    Page<SkuCatalogItemVo> pageSkuCatalog(SkuCatalogSearchDTO skuCatalogSearchDTO, Integer page, Integer size);
}
