package org.nott.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.nott.model.BizItemSkuRelation;
import org.nott.model.BizSkuCatalog;
import org.nott.model.BizSkuItem;
import org.nott.vo.ItemSkuVo;
import org.nott.service.mapper.BizItemSkuRelationMapper;
import org.nott.service.service.IBizItemSkuRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.nott.service.service.IBizSkuCatalogService;
import org.nott.service.service.IBizSkuItemService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
public class BizItemSkuRelationServiceImpl extends ServiceImpl<BizItemSkuRelationMapper, BizItemSkuRelation> implements IBizItemSkuRelationService {

    @Resource
    private IBizSkuCatalogService bizSkuCatalogService;
    @Resource
    private IBizSkuItemService bizSkuItemService;

    @Override
    public List<ItemSkuVo> selectItemSkuList(Long itemId) {
        List<ItemSkuVo> itemSkuVos = new ArrayList<>();
        LambdaQueryWrapper<BizItemSkuRelation> wrapper = new LambdaQueryWrapper<BizItemSkuRelation>().eq(BizItemSkuRelation::getItemId, itemId);
        List<BizItemSkuRelation> itemSkuRelations = this.list(wrapper);
        if(CollectionUtils.isEmpty(itemSkuRelations)){
            return itemSkuVos;
        }
        List<Long> catalogIds = itemSkuRelations.stream().map(BizItemSkuRelation::getSkuCatalogId).collect(Collectors.toList());
        Map<Long, Integer> catalogOrderMap = itemSkuRelations.stream().collect(Collectors.toMap(BizItemSkuRelation::getSkuCatalogId, BizItemSkuRelation::getDisplayOrder));
        List<BizSkuCatalog> skuCatalogs = bizSkuCatalogService.listByIds(catalogIds);
        if (skuCatalogs.isEmpty()) {
            return itemSkuVos;
        }
        Map<Long, BizSkuCatalog> bizSkuCatalogMap = skuCatalogs.stream().collect(Collectors.toMap(BizSkuCatalog::getId, element -> element, (key1, key2) -> key1));
        LambdaQueryWrapper<BizSkuItem> queryWrapper = new LambdaQueryWrapper<BizSkuItem>()
                .in(BizSkuItem::getSkuCatalogId, catalogIds);
        List<BizSkuItem> bizSkuItems = bizSkuItemService.list(queryWrapper);
        for (Long catalogId : catalogIds) {
            if (bizSkuCatalogMap.containsKey(catalogId)) {
                ItemSkuVo vo = new ItemSkuVo();
                BizSkuCatalog catalog = bizSkuCatalogMap.get(catalogId);
                List<BizSkuItem> skuItemList = bizSkuItems.stream().filter(r -> catalogId.equals(r.getSkuCatalogId())).collect(Collectors.toList());
                vo.setSkuCatalogName(catalog.getSkuCatalogName());
                vo.setSkuItems(skuItemList);
                vo.setDisplayOrder(catalogOrderMap.get(catalogId));
                itemSkuVos.add(vo);
            }
        }
        return itemSkuVos;
    }
}
