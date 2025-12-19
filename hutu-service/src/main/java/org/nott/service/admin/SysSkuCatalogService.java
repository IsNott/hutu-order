package org.nott.service.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.github.yulichang.query.MPJLambdaQueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.nott.model.SysShopInfo;
import org.nott.model.SysSkuCatalog;
import org.nott.service.mapper.admin.SysSkuCatalogMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import org.nott.dto.SysSkuCatalogDTO;
import org.nott.vo.SysSkuCatalogVo;


import javax.annotation.Resource;

/**
* sku分类表 Service
*/
@Service
public class SysSkuCatalogService extends ServiceImpl<SysSkuCatalogMapper, SysSkuCatalog>  {

    @Resource
    private SysSkuCatalogMapper sysSkuCatalogMapper;

    public IPage<SysSkuCatalogVo> queryPage(Integer page, Integer size, SysSkuCatalogDTO dto) {
        Long shopId = dto.getShopId();
        MPJLambdaWrapper<SysSkuCatalog> wrapper = new MPJLambdaWrapper<SysSkuCatalog>()
                .selectAll(SysSkuCatalog.class)
                .select(SysShopInfo::getShopName)
                .leftJoin(SysShopInfo.class, SysShopInfo::getId, SysSkuCatalog::getShopId)
                .eq(shopId != null, SysSkuCatalog::getShopId, shopId)
                .orderByAsc(SysSkuCatalog::getShowIndex);
        IPage<SysSkuCatalogVo> sysSkuCatalogVoPage = sysSkuCatalogMapper.selectJoinPage(new Page<>(page, size), SysSkuCatalogVo.class, wrapper);
        return sysSkuCatalogVoPage;
    }
}