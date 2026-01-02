package org.nott.service.admin;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.nott.model.SysDataDict;
import org.nott.service.mapper.admin.SysDataDictMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import org.nott.dto.SysDataDictDTO;
import org.nott.vo.SysDataDictVo;
import org.nott.common.utils.HutuUtils;
import org.nott.common.exception.HutuBizException;
import javax.annotation.Resource;
/**
* 数据字典 Service
*/
@Service
public class SysDataDictService extends ServiceImpl<SysDataDictMapper, SysDataDict>  {

    @Resource
    private SysDataDictMapper sysDataDictMapper;

    public IPage<SysDataDictVo> queryPage(Integer page, Integer size, SysDataDictDTO dto) {
        MPJLambdaWrapper<SysDataDict> wrapper = new MPJLambdaWrapper<SysDataDict>()
            .selectAll(SysDataDict.class)
            .orderByDesc(SysDataDict::getCreateTime);
        return sysDataDictMapper.selectJoinPage(new Page<>(page, size), SysDataDictVo.class, wrapper);
    }


    public SysDataDictVo save(SysDataDictDTO dto) {
        SysDataDict entity = HutuUtils.transToObject(dto, SysDataDict.class);
        entity.setDelFlag(false);
        this.save(entity);
        return HutuUtils.transToObject(entity, SysDataDictVo.class);
    }

    public SysDataDictVo update(SysDataDictDTO dto) {
        Long id = dto.getId();
        SysDataDict entity = this.getById(id);
        if(HutuUtils.isEmpty(entity)){
            throw new HutuBizException("Entity not found.");
        }
        HutuUtils.copyProperties(dto, entity);
        this.updateById(entity);
        return HutuUtils.transToObject(entity, SysDataDictVo.class);
    }
}