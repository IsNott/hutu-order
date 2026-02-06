package org.nott.service.api;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.nott.dto.SysItemSkuOptionDTO;
import org.nott.model.BizItemSkuOption;
import org.nott.model.SysItemSkuOption;
import org.nott.service.mapper.api.BizItemSkuOptionMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import org.nott.dto.BizItemSkuOptionDTO;
import org.nott.vo.BizItemSkuOptionVo;
import org.nott.common.utils.HutuUtils;
import org.nott.common.exception.HutuBizException;
import javax.annotation.Resource;
import java.util.List;

/**
* SKU规格选项表 Service
*/
@Service
public class BizItemSkuOptionService extends ServiceImpl<BizItemSkuOptionMapper, BizItemSkuOption>  {

    @Resource
    private BizItemSkuOptionMapper bizItemSkuOptionMapper;
}