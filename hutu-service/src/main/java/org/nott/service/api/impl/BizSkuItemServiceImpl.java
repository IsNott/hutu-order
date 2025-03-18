package org.nott.service.api.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.nott.common.utils.HutuUtils;
import org.nott.model.BizSkuCatalogRelation;
import org.nott.model.BizSkuItem;
import org.nott.service.mapper.api.BizSkuCatalogRelationMapper;
import org.nott.service.mapper.api.BizSkuItemMapper;
import org.nott.service.api.IBizSkuItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author nott
 * @since 2024-06-03
 */
@Service
public class BizSkuItemServiceImpl extends ServiceImpl<BizSkuItemMapper, BizSkuItem> implements IBizSkuItemService {

    @Resource
    private BizSkuItemMapper bizSkuItemMapper;
    @Resource
    private BizSkuCatalogRelationMapper bizSkuCatalogRelationMapper;

    @Override
    public Map<Long,List<BizSkuItem>> queryByCatalogIds(List<Long> catalogIds) {
        Map<Long, List<BizSkuItem>> relationMap = new HashMap<>(16);

        LambdaQueryWrapper<BizSkuCatalogRelation> wrapper = new LambdaQueryWrapper<BizSkuCatalogRelation>().in(BizSkuCatalogRelation::getSkuCatalogId, catalogIds);
        List<BizSkuCatalogRelation> relationList = bizSkuCatalogRelationMapper.selectList(wrapper);
        if (HutuUtils.isEmpty(relationList)) {
            return null;
        }
        List<Long> bizItemIds = relationList.stream().map(BizSkuCatalogRelation::getSkuItemId).collect(Collectors.toList());
        List<BizSkuItem> skuItems = bizSkuItemMapper.selectList(new LambdaQueryWrapper<BizSkuItem>().in(BizSkuItem::getId, bizItemIds));
        for (BizSkuCatalogRelation relation : relationList) {
            Long skuCatalogId = relation.getSkuCatalogId();
            Long skuItemId = relation.getSkuItemId();
            List<BizSkuItem> currentSkuItem = skuItems.stream().filter(r -> skuItemId.equals(r.getId())).collect(Collectors.toList());
            if(relationMap.containsKey(skuCatalogId)){
                List<BizSkuItem> bizSkuItems = relationMap.get(skuCatalogId);
                bizSkuItems.addAll(currentSkuItem);
            }else {
                relationMap.put(skuCatalogId,currentSkuItem);
            }
        }
        return relationMap;
    }
}
