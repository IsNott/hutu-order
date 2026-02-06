package org.nott.service.admin;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.nott.dto.SysItemSkuOptionDTO;
import org.nott.model.SysItemSkuOption;
import org.nott.model.SysItemSkuSpec;
import org.nott.service.mapper.admin.SysItemSkuSpecMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.nott.vo.SysItemSkuOptionVo;
import org.nott.vo.SysItemSkuSpecWithOptionVo;
import org.springframework.stereotype.Service;
import org.nott.dto.SysItemSkuSpecDTO;
import org.nott.vo.SysItemSkuSpecVo;
import org.nott.common.utils.HutuUtils;
import org.nott.common.exception.HutuBizException;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* 商品SKU规格表 Service
*/
@Service
public class SysItemSkuSpecService extends ServiceImpl<SysItemSkuSpecMapper, SysItemSkuSpec>  {

    @Resource
    private SysItemSkuSpecMapper sysItemSkuSpecMapper;
    @Resource
    private SysItemSkuOptionService sysItemSkuOptionService;

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

    public List<SysItemSkuSpecVo> querySkuInfoByProductId(Long id) {
        List<SysItemSkuSpecVo> result = new ArrayList<>();
        LambdaQueryWrapper<SysItemSkuSpec> wrapper = new LambdaQueryWrapper<SysItemSkuSpec>()
            .eq(SysItemSkuSpec::getProductId, id)
            .orderByAsc(SysItemSkuSpec::getSortOrder);
        List<SysItemSkuSpec> sysItemSkuSpecs = this.list(wrapper);
        if(HutuUtils.isEmpty(sysItemSkuSpecs)){
            return new ArrayList<>();
        }
        List<Long> specIds = sysItemSkuSpecs.stream().map(SysItemSkuSpec::getId).collect(Collectors.toList());
        LambdaQueryWrapper<SysItemSkuOption> optWrapper = new LambdaQueryWrapper<>();
        optWrapper.in(SysItemSkuOption::getSpecId, specIds)
            .orderByAsc(SysItemSkuOption::getSortOrder);
        List<SysItemSkuOption> optionList = sysItemSkuOptionService.list(optWrapper);
        for (SysItemSkuSpec sysItemSkuSpec : sysItemSkuSpecs) {
            Long specId = sysItemSkuSpec.getId();
            List<SysItemSkuOption> childOpt = optionList.stream()
                    .filter(option -> specId.equals(option.getSpecId()))
                    .collect(Collectors.toList());
            if(HutuUtils.isEmpty(childOpt)){
                continue;
            }
            SysItemSkuSpecVo sysItemSkuSpecVo = HutuUtils.transToObject(sysItemSkuSpec, SysItemSkuSpecVo.class);
            sysItemSkuSpecVo.setSkuOptionList(HutuUtils.transToList(childOpt, SysItemSkuOptionVo.class));
            result.add(sysItemSkuSpecVo);
        }
        return result;
    }

    public void setProductSkuRelation(List<SysItemSkuSpecDTO> skuSpecs, Long id) {
        LambdaQueryWrapper<SysItemSkuSpec> wrapper = new LambdaQueryWrapper<SysItemSkuSpec>()
                .eq(SysItemSkuSpec::getProductId, id);
        this.remove(wrapper);
        for (SysItemSkuSpecDTO skuSpecDTO : skuSpecs) {
            SysItemSkuSpec entity = HutuUtils.transToObject(skuSpecDTO, SysItemSkuSpec.class);
            entity.setProductId(id);
            entity.setDelFlag(false);
            this.save(entity);
            List<SysItemSkuOptionDTO> optionVos = skuSpecDTO.getSkuOptionList();
            if (HutuUtils.isNotEmpty(optionVos)) {
                sysItemSkuOptionService.setSpecOptionRelation(optionVos, entity.getId());
            }
        }
    }
}