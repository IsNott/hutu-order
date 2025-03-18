package org.nott.service.api.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.nott.model.BizSkuCatalogRelation;
import org.nott.service.mapper.api.BizSkuCatalogRelationMapper;
import org.nott.service.api.IBizSkuCatalogRelationService;
import org.springframework.stereotype.Service;

/**
 * @author Nott
 * @date 2024-6-18
 */

@Service
public class BizSkuCatalogRelationServiceImpl extends ServiceImpl<BizSkuCatalogRelationMapper, BizSkuCatalogRelation> implements IBizSkuCatalogRelationService {
}
