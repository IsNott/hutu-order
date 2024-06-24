package org.nott.service.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.nott.dto.SkuCatalogSearchDTO;
import org.nott.model.BizSkuCatalog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.nott.vo.SkuCatalogItemVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author nott
 * @since 2024-06-03
 */
public interface BizSkuCatalogMapper extends BaseMapper<BizSkuCatalog> {

    Page<SkuCatalogItemVo> selectCatalogItemByPage(IPage<SkuCatalogItemVo> page, @Param("skuCatalogSearchDTO") SkuCatalogSearchDTO skuCatalogSearchDTO, @Param("offset") int offset, @Param("size") Integer size);
}
