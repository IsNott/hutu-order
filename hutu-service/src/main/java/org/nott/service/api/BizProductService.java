package org.nott.service.api;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.nott.model.BizMenu;
import org.nott.model.BizProduct;
import org.nott.service.mapper.api.BizProductMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import org.nott.dto.BizProductDTO;
import org.nott.vo.BizProductVo;
import org.nott.common.utils.HutuUtils;
import org.nott.common.exception.HutuBizException;
import javax.annotation.Resource;
/**
* 商品表 Service
*/
@Service
public class BizProductService extends ServiceImpl<BizProductMapper, BizProduct>  {

    @Resource
    private BizProductMapper bizProductMapper;

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
}