package org.nott.service.api.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.nott.common.utils.HutuUtils;
import org.nott.dto.SkuCatalogSearchDTO;
import org.nott.model.BizItemSkuRelation;
import org.nott.model.BizSkuCatalog;
import org.nott.model.BizSkuItem;
import org.nott.service.mapper.api.BizSkuCatalogMapper;
import org.nott.service.api.IBizItemSkuRelationService;
import org.nott.service.api.IBizSkuCatalogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.nott.service.api.IBizSkuItemService;
import org.nott.vo.SkuCatalogItemVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author nott
 * @since 2024-06-03
 */
@Service
public class BizSkuCatalogServiceImpl extends ServiceImpl<BizSkuCatalogMapper, BizSkuCatalog> implements IBizSkuCatalogService {

    @Resource
    private IBizSkuItemService bizSkuItemService;
    @Resource
    private IBizItemSkuRelationService bizItemSkuRelationService;
    @Resource
    private BizSkuCatalogMapper bizSkuCatalogMapper;

    @Override
    public Page<SkuCatalogItemVo> pageSkuCatalog(SkuCatalogSearchDTO skuCatalogSearchDTO, Integer page, Integer size) {
        int offset = HutuUtils.pageOffset(page, size);
        IPage<SkuCatalogItemVo> itemVoPage = new Page<>(page, size);
        Page<SkuCatalogItemVo> voPage = bizSkuCatalogMapper.selectCatalogItemByPage(itemVoPage,skuCatalogSearchDTO,offset,size);
        if(HutuUtils.isNotEmpty(voPage.getRecords())){
            List<SkuCatalogItemVo> records = voPage.getRecords();
            List<Long> catalogIds = records.stream().map(SkuCatalogItemVo::getSkuCatalogId).collect(Collectors.toList());
            Map<Long, List<BizSkuItem>> catalogItemMap = bizSkuItemService.queryByCatalogIds(catalogIds);
            for (SkuCatalogItemVo vo : records) {
                Long id = vo.getSkuCatalogId();
                List<BizSkuItem> bizSkuItems = catalogItemMap.get(id);
                vo.setSkuItems(bizSkuItems);
            }
        }
        return voPage;
    }

    @Override
    public void deleteSkuCatalog(Long id) {
        LambdaQueryWrapper<BizItemSkuRelation> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BizItemSkuRelation::getSkuCatalogId,id);
        List<BizItemSkuRelation> skuRelations = bizItemSkuRelationService.list(wrapper);
        List<Long> relationIds = skuRelations.stream().map(BizItemSkuRelation::getId).collect(Collectors.toList());
        List<Long> itemIds = skuRelations.stream().map(BizItemSkuRelation::getItemId).collect(Collectors.toList());

    }
}
