package org.nott.service.admin;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.nott.model.SysItemSkuSpec;
import org.nott.service.mapper.admin.SysItemSkuSpecMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import org.nott.dto.SysItemSkuSpecDTO;
import org.nott.vo.SysItemSkuSpecVo;
import org.nott.common.utils.HutuUtils;
import org.nott.common.exception.HutuBizException;
import javax.annotation.Resource;
/**
* 商品SKU规格表 Service
*/
@Service
public class SysItemSkuSpecService extends ServiceImpl<SysItemSkuSpecMapper, SysItemSkuSpec>  {

    @Resource
    private SysItemSkuSpecMapper sysItemSkuSpecMapper;

    public IPage<SysItemSkuSpecVo> queryPage(Integer page, Integer size, SysItemSkuSpecDTO dto) {
        MPJLambdaWrapper<SysItemSkuSpec> wrapper = new MPJLambdaWrapper<SysItemSkuSpec>()
            .selectAll(SysItemSkuSpec.class)
            .orderByDesc(SysItemSkuSpec::getCreateTime);
        return sysItemSkuSpecMapper.selectJoinPage(new Page<>(page, size), SysItemSkuSpecVo.class, wrapper);
    }


    public SysItemSkuSpecVo save(SysItemSkuSpecDTO dto) {
        SysItemSkuSpec entity = HutuUtils.transToObject(dto, SysItemSkuSpec.class);
        entity.setDelFlag(false);
        this.save(entity);
        return HutuUtils.transToObject(entity, SysItemSkuSpecVo.class);
    }

    public SysItemSkuSpecVo update(SysItemSkuSpecDTO dto) {
        Long id = dto.getId();
        SysItemSkuSpec entity = this.getById(id);
        if(HutuUtils.isEmpty(entity)){
            throw new HutuBizException("Entity not found.");
        }
        HutuUtils.copyProperties(dto, entity);
        this.updateById(entity);
        return HutuUtils.transToObject(entity, SysItemSkuSpecVo.class);
    }
}