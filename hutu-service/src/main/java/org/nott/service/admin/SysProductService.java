package org.nott.service.admin;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.nott.dto.SysProductDTO;
import org.nott.dto.UserProfileDTO;
import org.nott.model.SysProduct;
import org.nott.service.mapper.admin.SysProductMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import org.nott.vo.SysProductVo;
import org.nott.common.utils.HutuUtils;
import org.nott.common.exception.HutuBizException;
import javax.annotation.Resource;
/**
* 商品表 Service
*/
@Service
public class SysProductService extends ServiceImpl<SysProductMapper, SysProduct>  {

    @Resource
    private SysProductMapper sysProductMapper;

    public IPage<SysProductVo> queryPage(Integer page, Integer size, SysProductDTO dto) {
        MPJLambdaWrapper<SysProduct> wrapper = new MPJLambdaWrapper<SysProduct>()
            .selectAll(SysProduct.class)
            .orderByDesc(SysProduct::getCreateTime);
        return sysProductMapper.selectJoinPage(new Page<>(page, size), SysProductVo.class, wrapper);
    }


    public SysProductVo save(SysProductDTO dto) {
        SysProduct entity = HutuUtils.transToObject(dto, SysProduct.class);
        entity.setDelFlag(false);
        this.save(entity);
        return HutuUtils.transToObject(entity, SysProductVo.class);
    }

    public SysProductVo update(SysProductDTO dto) {
        Long id = dto.getId();
        SysProduct entity = this.getById(id);
        if(HutuUtils.isEmpty(entity)){
            throw new HutuBizException("Entity not found.");
        }
        HutuUtils.copyProperties(dto, entity);
        this.updateById(entity);
        return HutuUtils.transToObject(entity, SysProductVo.class);
    }
}