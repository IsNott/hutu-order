package org.nott.service.api.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.nott.common.utils.HutuUtils;
import org.nott.model.BizCommonRemark;
import org.nott.service.api.IBizCommonRemarkService;
import org.nott.service.mapper.api.BizCommonRemarkMapper;
import org.nott.vo.CommonRemarkVo;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 常用备注表 服务实现类
 * @author Nott
 * @date 2025-3-24
 */
@Service
public class BizCommonRemarkServiceImpl extends ServiceImpl<BizCommonRemarkMapper, BizCommonRemark> implements IBizCommonRemarkService {

    @Override
    public List<CommonRemarkVo> queryCommonRemark(int size) {
        LambdaQueryWrapper<BizCommonRemark> wrapper = new LambdaQueryWrapper<BizCommonRemark>().eq(BizCommonRemark::getDelFlag, 0).last("limit " + size);
        List<BizCommonRemark> list = this.list(wrapper);
        return HutuUtils.transToVos(list, CommonRemarkVo.class);
    }
}
