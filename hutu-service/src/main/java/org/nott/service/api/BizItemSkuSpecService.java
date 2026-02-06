package org.nott.service.api;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.nott.model.BizItemSkuOption;
import org.nott.model.BizItemSkuSpec;
import org.nott.service.mapper.api.BizItemSkuSpecMapper;
import org.nott.vo.BizItemSkuOptionVo;
import org.nott.vo.SysItemSkuOptionVo;
import org.springframework.stereotype.Service;
import org.nott.vo.BizItemSkuSpecVo;
import org.nott.common.utils.HutuUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* 商品SKU规格表 Service
*/
@Service
public class BizItemSkuSpecService extends ServiceImpl<BizItemSkuSpecMapper, BizItemSkuSpec>  {

    @Resource
    private BizItemSkuSpecMapper bizItemSkuSpecMapper;
    @Resource
    private BizItemSkuOptionService bizItemSkuOptionService;

    public List<BizItemSkuSpecVo> querySkuInfoByProductId(Long id) {
        List<BizItemSkuSpecVo> result = new ArrayList<>();
        LambdaQueryWrapper<BizItemSkuSpec> wrapper = new LambdaQueryWrapper<BizItemSkuSpec>()
                .eq(BizItemSkuSpec::getProductId, id)
                .orderByAsc(BizItemSkuSpec::getSortOrder);
        List<BizItemSkuSpec> bizItemSkuSpecs = this.list(wrapper);
        if(HutuUtils.isEmpty(bizItemSkuSpecs)){
            return new ArrayList<>();
        }
        List<Long> specIds = bizItemSkuSpecs.stream().map(BizItemSkuSpec::getId).collect(Collectors.toList());
        LambdaQueryWrapper<BizItemSkuOption> optWrapper = new LambdaQueryWrapper<>();
        optWrapper.in(BizItemSkuOption::getSpecId, specIds)
                .orderByAsc(BizItemSkuOption::getSortOrder);
        List<BizItemSkuOption> optionList = bizItemSkuOptionService.list(optWrapper);
        for (BizItemSkuSpec sysItemSkuSpec : bizItemSkuSpecs) {
            Long specId = sysItemSkuSpec.getId();
            List<BizItemSkuOption> childOpt = optionList.stream()
                    .filter(option -> specId.equals(option.getSpecId()))
                    .collect(Collectors.toList());
            if(HutuUtils.isEmpty(childOpt)){
                continue;
            }
            BizItemSkuSpecVo bizItemSkuSpecVo = HutuUtils.transToObject(sysItemSkuSpec, BizItemSkuSpecVo.class);
            bizItemSkuSpecVo.setSkuOptionList(HutuUtils.transToList(childOpt, BizItemSkuOptionVo.class));
            result.add(bizItemSkuSpecVo);
        }
        return result;
    }
}