package org.nott.service.api;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.nott.model.BizSlideShow;
import org.nott.service.mapper.api.BizSlideShowMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import org.nott.dto.BizSlideShowDTO;
import org.nott.vo.BizSlideShowVo;
import org.nott.common.utils.HutuUtils;
import org.nott.common.exception.HutuBizException;
import javax.annotation.Resource;
/**
*  Service
*/
@Service
public class BizSlideShowService extends ServiceImpl<BizSlideShowMapper, BizSlideShow>  {

    @Resource
    private BizSlideShowMapper bizSlideShowMapper;
}