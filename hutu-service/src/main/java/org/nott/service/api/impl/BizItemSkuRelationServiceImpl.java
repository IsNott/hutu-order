package org.nott.service.api.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.nott.model.BizItemSkuRelation;
import org.nott.model.BizSkuCatalog;
import org.nott.model.BizSkuCatalogRelation;
import org.nott.model.BizSkuItem;
import org.nott.service.api.IBizSkuCatalogRelationService;
import org.nott.vo.ItemSkuVo;
import org.nott.service.mapper.api.BizItemSkuRelationMapper;
import org.nott.service.api.IBizItemSkuRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.nott.service.api.IBizSkuCatalogService;
import org.nott.service.api.IBizSkuItemService;
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

    @Resource
    private IBizSkuCatalogRelationService bizSkuCatalogRelationService;

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
        LambdaQueryWrapper<BizSkuCatalogRelation> queryWrapper = new LambdaQueryWrapper<BizSkuCatalogRelation>()
                .in(BizSkuCatalogRelation::getSkuCatalogId, catalogIds);
        List<BizSkuCatalogRelation> bizSkuCatalogRelations = bizSkuCatalogRelationService.list(queryWrapper);

        List<Long> skuItemIds = bizSkuCatalogRelations.stream().map(BizSkuCatalogRelation::getSkuItemId).collect(Collectors.toList());
        List<BizSkuItem> bizSkuItems = bizSkuItemService.listByIds(skuItemIds);
        for (BizSkuCatalogRelation relation : bizSkuCatalogRelations) {
            Long catalogId = relation.getSkuCatalogId();
            if (bizSkuCatalogMap.containsKey(catalogId)) {
                Long skuItemId = relation.getSkuItemId();
                ItemSkuVo vo = new ItemSkuVo();
                BizSkuCatalog catalog = bizSkuCatalogMap.get(catalogId);
                ItemSkuVo itemSkuVo = itemSkuVos.stream().filter(r -> catalogId.equals(r.getCatalogId())).findFirst().orElse(null);
                List<BizSkuItem> skuItemList = bizSkuItems.stream().filter(r -> skuItemId.equals(r.getId())).collect(Collectors.toList());
                if(itemSkuVo != null){
                    itemSkuVo.getSkuItems().addAll(skuItemList);
                }else {
                    List<BizSkuItem> skuItems = new ArrayList<>();
                    skuItems.addAll(skuItemList);
                    vo.setSkuCatalogName(catalog.getSkuCatalogName());
                    vo.setSkuItems(skuItemList);
                    vo.setCatalogId(catalogId);
                    vo.setDisplayOrder(catalogOrderMap.get(catalogId));
                    vo.setSkuItems(skuItems);
                    itemSkuVos.add(vo);
                }

            }
        }
        return itemSkuVos;
    }
}
