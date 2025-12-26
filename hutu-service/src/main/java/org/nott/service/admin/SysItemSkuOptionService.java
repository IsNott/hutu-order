package org.nott.service.admin;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.nott.model.SysItemSkuOption;
import org.nott.service.mapper.admin.SysItemSkuOptionMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import org.nott.dto.SysItemSkuOptionDTO;
import org.nott.vo.SysItemSkuOptionVo;
import org.nott.common.utils.HutuUtils;
import org.nott.common.exception.HutuBizException;
import javax.annotation.Resource;
/**
* SKU规格选项表 Service
*/
@Service
public class SysItemSkuOptionService extends ServiceImpl<SysItemSkuOptionMapper, SysItemSkuOption>  {

    @Resource
    private SysItemSkuOptionMapper sysItemSkuOptionMapper;

    public IPage<SysItemSkuOptionVo> queryPage(Integer page, Integer size, SysItemSkuOptionDTO dto) {
        MPJLambdaWrapper<SysItemSkuOption> wrapper = new MPJLambdaWrapper<SysItemSkuOption>()
            .selectAll(SysItemSkuOption.class)
            .orderByDesc(SysItemSkuOption::getCreateTime);
        return sysItemSkuOptionMapper.selectJoinPage(new Page<>(page, size), SysItemSkuOptionVo.class, wrapper);
    }


    public SysItemSkuOptionVo save(SysItemSkuOptionDTO dto) {
        SysItemSkuOption entity = HutuUtils.transToObject(dto, SysItemSkuOption.class);
        entity.setDelFlag(false);
        this.save(entity);
        return HutuUtils.transToObject(entity, SysItemSkuOptionVo.class);
    }

    public SysItemSkuOptionVo update(SysItemSkuOptionDTO dto) {
        Long id = dto.getId();
        SysItemSkuOption entity = this.getById(id);
        if(HutuUtils.isEmpty(entity)){
            throw new HutuBizException("Entity not found.");
        }
        HutuUtils.copyProperties(dto, entity);
        this.updateById(entity);
        return HutuUtils.transToObject(entity, SysItemSkuOptionVo.class);
    }
}