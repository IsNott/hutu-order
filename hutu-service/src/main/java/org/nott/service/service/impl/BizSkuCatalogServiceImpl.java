package org.nott.service.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.nott.common.utils.HutuUtils;
import org.nott.dto.SkuCatalogSearchDTO;
import org.nott.model.BizSkuCatalog;
import org.nott.service.mapper.BizSkuCatalogMapper;
import org.nott.service.service.IBizSkuCatalogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.nott.vo.SkuCatalogItemVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
    private BizSkuCatalogMapper bizSkuCatalogMapper;

    @Override
    public Page<SkuCatalogItemVo> pageSkuCatalog(SkuCatalogSearchDTO skuCatalogSearchDTO, Integer page, Integer size) {
        int offset = HutuUtils.pageOffset(page, size);
        List<SkuCatalogItemVo> vos = bizSkuCatalogMapper.selectCatalogItemByPage(skuCatalogSearchDTO,offset,size);
        Page<SkuCatalogItemVo> voPage = new Page<>(page,size);
        if(HutuUtils.isNotEmpty(vos)){
            voPage.setRecords(vos);
        }
        return voPage;
    }
}
