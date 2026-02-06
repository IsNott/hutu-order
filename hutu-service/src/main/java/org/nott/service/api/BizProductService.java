package org.nott.service.api;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.nott.common.ResponseEntity;
import org.nott.feign.OssAccessClient;
import org.nott.model.BizMenu;
import org.nott.model.BizProduct;
import org.nott.service.mapper.api.BizProductMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.nott.vo.*;
import org.springframework.stereotype.Service;
import org.nott.dto.BizProductDTO;
import org.nott.common.utils.HutuUtils;
import javax.annotation.Resource;
import java.util.List;

/**
* 商品表 Service
*/
@Service
public class BizProductService extends ServiceImpl<BizProductMapper, BizProduct>  {

    @Resource
    private BizProductMapper bizProductMapper;
    @Resource
    private OssAccessClient ossClient;
    @Resource
    private BizItemSkuSpecService bizItemSkuSpecService;

    public IPage<BizProductVo> queryPage(BizProductDTO dto, Integer page, Integer size) {
        String keyWord = dto.getKeyWord();
        Long shopId = dto.getShopId();
        MPJLambdaWrapper<BizProduct> wrapper = new MPJLambdaWrapper<BizProduct>()
                .selectAll(BizProduct.class)
                .leftJoin(BizMenu.class, BizMenu::getItemId, BizProduct::getId)
                .eq(BizMenu::getShopId, shopId)
                .like(HutuUtils.isNotEmpty(keyWord), BizProduct::getItemName, keyWord);
        Page<BizProductVo> voPage = wrapper.page(new Page<>(page, size), BizProductVo.class);
        return voPage;
    }

    public BizProductVo details(Long id) {
        BizProductVo vo = HutuUtils.transToObject(this.getById(id), BizProductVo.class);
        List<BizItemSkuSpecVo> skuSpecVos = bizItemSkuSpecService.querySkuInfoByProductId(vo.getId());
        vo.setSkuSpecs(skuSpecVos);
        ResponseEntity<List<OssFileVo>> response = ossClient.getByBizId(vo.getId());
        if (response.isSuccess()) {
            vo.setImages(response.getData());
        }
        return vo;
    }
}