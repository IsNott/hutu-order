package org.nott.service.api;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.nott.model.BizSlideShow;
import org.nott.model.BizSlideShowItem;
import org.nott.service.mapper.api.BizSlideShowItemMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import org.nott.dto.BizSlideShowItemDTO;
import org.nott.vo.BizSlideShowItemVo;
import org.nott.common.utils.HutuUtils;
import org.nott.common.exception.HutuBizException;

import javax.annotation.Resource;
import java.util.List;

/**
 * Service
 */
@Service
public class BizSlideShowItemService extends ServiceImpl<BizSlideShowItemMapper, BizSlideShowItem> {

    @Resource
    private BizSlideShowItemMapper bizSlideShowItemMapper;

    public List<BizSlideShowItemVo> getByType(Integer type) {
        MPJLambdaWrapper<BizSlideShowItem> mpjLambdaWrapper = new MPJLambdaWrapper<BizSlideShowItem>()
                .select(BizSlideShow::getType)
                .selectAll(BizSlideShowItem.class)
                .leftJoin(BizSlideShow.class, BizSlideShow::getId, BizSlideShowItem::getSlideShowId)
                .eq(BizSlideShow::getType, type)
                .orderByAsc(BizSlideShowItem::getSortOrder);
        List<BizSlideShowItemVo> list = bizSlideShowItemMapper.selectJoinList(BizSlideShowItemVo.class, mpjLambdaWrapper);
        return list;
    }
}