package org.nott.service.admin;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.nott.model.SysSlideShowItem;
import org.nott.service.mapper.admin.SysSlideShowItemMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import org.nott.dto.SysSlideShowItemDTO;
import org.nott.vo.SysSlideShowItemVo;
import org.nott.common.utils.HutuUtils;
import org.nott.common.exception.HutuBizException;
import javax.annotation.Resource;
/**
* 轮播图关联内容 Service
*/
@Service
public class SysSlideShowItemService extends ServiceImpl<SysSlideShowItemMapper, SysSlideShowItem>  {

    @Resource
    private SysSlideShowItemMapper sysSlideShowItemMapper;

    public IPage<SysSlideShowItemVo> queryPage(Integer page, Integer size, SysSlideShowItemDTO dto) {
        MPJLambdaWrapper<SysSlideShowItem> wrapper = new MPJLambdaWrapper<SysSlideShowItem>()
            .selectAll(SysSlideShowItem.class)
            .orderByDesc(SysSlideShowItem::getCreateTime);
        return sysSlideShowItemMapper.selectJoinPage(new Page<>(page, size), SysSlideShowItemVo.class, wrapper);
    }


    public SysSlideShowItemVo save(SysSlideShowItemDTO dto) {
        SysSlideShowItem entity = HutuUtils.transToObject(dto, SysSlideShowItem.class);
        entity.setDelFlag(false);
        this.save(entity);
        return HutuUtils.transToObject(entity, SysSlideShowItemVo.class);
    }

    public SysSlideShowItemVo update(SysSlideShowItemDTO dto) {
        Long id = dto.getId();
        SysSlideShowItem entity = this.getById(id);
        if(HutuUtils.isEmpty(entity)){
            throw new HutuBizException("Entity not found.");
        }
        HutuUtils.copyProperties(dto, entity);
        this.updateById(entity);
        return HutuUtils.transToObject(entity, SysSlideShowItemVo.class);
    }
}