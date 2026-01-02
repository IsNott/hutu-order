package org.nott.service.admin;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.nott.model.SysSlideShow;
import org.nott.service.mapper.admin.SysSlideShowMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import org.nott.dto.SysSlideShowDTO;
import org.nott.vo.SysSlideShowVo;
import org.nott.common.utils.HutuUtils;
import org.nott.common.exception.HutuBizException;
import javax.annotation.Resource;
/**
*  Service
*/
@Service
public class SysSlideShowService extends ServiceImpl<SysSlideShowMapper, SysSlideShow>  {

    @Resource
    private SysSlideShowMapper sysSlideShowMapper;

    public IPage<SysSlideShowVo> queryPage(Integer page, Integer size, SysSlideShowDTO dto) {
        MPJLambdaWrapper<SysSlideShow> wrapper = new MPJLambdaWrapper<SysSlideShow>()
            .selectAll(SysSlideShow.class)
            .orderByDesc(SysSlideShow::getCreateTime);
        return sysSlideShowMapper.selectJoinPage(new Page<>(page, size), SysSlideShowVo.class, wrapper);
    }


    public SysSlideShowVo save(SysSlideShowDTO dto) {
        SysSlideShow entity = HutuUtils.transToObject(dto, SysSlideShow.class);
        entity.setDelFlag(false);
        this.save(entity);
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
        return HutuUtils.transToObject(entity, SysSlideShowVo.class);
    }
}