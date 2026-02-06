package org.nott.service.admin;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.nott.dto.SysSlideShowItemDTO;
import org.nott.model.SysSlideShow;
import org.nott.model.SysSlideShowItem;
import org.nott.service.mapper.admin.SysSlideShowItemMapper;
import org.nott.service.mapper.admin.SysSlideShowMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.nott.vo.SysSlideShowItemVo;
import org.springframework.stereotype.Service;
import org.nott.dto.SysSlideShowDTO;
import org.nott.vo.SysSlideShowVo;
import org.nott.common.utils.HutuUtils;
import org.nott.common.exception.HutuBizException;
import javax.annotation.Resource;
import java.util.List;

/**
*  Service
*/
@Service
public class SysSlideShowService extends ServiceImpl<SysSlideShowMapper, SysSlideShow>  {

    @Resource
    private SysSlideShowMapper sysSlideShowMapper;
    @Resource
    private SysSlideShowItemMapper sysSlideShowItemMapper;

    public List<SysSlideShowVo> queryList(SysSlideShowDTO dto) {
        MPJLambdaWrapper<SysSlideShow> wrapper = new MPJLambdaWrapper<SysSlideShow>()
            .selectAll(SysSlideShow.class)
            .orderByDesc(SysSlideShow::getCreateTime);
        return sysSlideShowMapper.selectJoinList(SysSlideShowVo.class, wrapper);
    }

    public IPage<SysSlideShowVo> queryPage(Integer page, Integer size, SysSlideShowDTO dto) {
        MPJLambdaWrapper<SysSlideShow> wrapper = new MPJLambdaWrapper<SysSlideShow>()
            .selectAll(SysSlideShow.class)
            .orderByDesc(SysSlideShow::getCreateTime);
        return sysSlideShowMapper.selectJoinPage(new Page<>(page, size), SysSlideShowVo.class, wrapper);
    }


    public SysSlideShowVo save(SysSlideShowDTO dto) {
        Integer type = dto.getType();
        LambdaQueryWrapper<SysSlideShow> wrapper = new LambdaQueryWrapper<SysSlideShow>().eq(SysSlideShow::getType, type).last("limit 1");
        SysSlideShow exist = this.getOne(wrapper);
        if(HutuUtils.isNotEmpty(exist)){
            throw new HutuBizException("已存在相同类型的轮播图。");
        }
        SysSlideShow entity = HutuUtils.transToObject(dto, SysSlideShow.class);
        entity.setDelFlag(false);
        this.save(entity);
        List<SysSlideShowItemDTO> slideShowItems = dto.getSlideShowItems();
        if(HutuUtils.isNotEmpty(slideShowItems)){
            for(SysSlideShowItemDTO itemDTO : slideShowItems){
                itemDTO.setSlideShowId(entity.getId());
                itemDTO.setDelFlag(false);
                sysSlideShowItemMapper.insert(HutuUtils.transToObject(itemDTO, SysSlideShowItem.class));
            }
        }
        return HutuUtils.transToObject(entity, SysSlideShowVo.class);
    }

    public SysSlideShowVo update(SysSlideShowDTO dto) {
        Long id = dto.getId();
        SysSlideShow entity = this.getById(id);
        if(HutuUtils.isEmpty(entity)){
            throw new HutuBizException("Entity not found.");
        }
        HutuUtils.copyProperties(dto, entity);
        this.updateById(entity);
        List<SysSlideShowItemDTO> slideShowItems = dto.getSlideShowItems();
        LambdaQueryWrapper<SysSlideShowItem> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysSlideShowItem::getSlideShowId, id);
        sysSlideShowItemMapper.delete(queryWrapper);
        if(HutuUtils.isNotEmpty(slideShowItems)){
            for(SysSlideShowItemDTO itemDTO : slideShowItems){
                itemDTO.setSlideShowId(entity.getId());
                itemDTO.setDelFlag(false);
                sysSlideShowItemMapper.insert(HutuUtils.transToObject(itemDTO, SysSlideShowItem.class));
            }
        }
        return HutuUtils.transToObject(entity, SysSlideShowVo.class);
    }

    public SysSlideShowVo details(Long id) {
        SysSlideShowVo vo = HutuUtils.transToObject(this.getById(id), SysSlideShowVo.class);
        LambdaQueryWrapper<SysSlideShowItem> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysSlideShowItem::getSlideShowId, id);
        List<SysSlideShowItem> showItems = sysSlideShowItemMapper.selectList(queryWrapper);
        vo.setSlideShowItems(HutuUtils.transToList(showItems, SysSlideShowItemVo.class));
        return vo;
    }
}