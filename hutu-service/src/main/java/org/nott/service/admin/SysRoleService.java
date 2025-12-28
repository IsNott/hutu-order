package org.nott.service.admin;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.nott.model.SysRole;
import org.nott.model.SysRoleMenu;
import org.nott.service.mapper.admin.SysRoleMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import org.nott.dto.SysRoleDTO;
import org.nott.vo.SysRoleVo;
import org.nott.common.utils.HutuUtils;
import org.nott.common.exception.HutuBizException;
import javax.annotation.Resource;
import java.util.List;

/**
* 系统角色表 Service
*/
@Service
public class SysRoleService extends ServiceImpl<SysRoleMapper, SysRole>  {

    @Resource
    private SysRoleMapper sysRoleMapper;

    public IPage<SysRoleVo> queryPage(Integer page, Integer size, SysRoleDTO dto) {
        MPJLambdaWrapper<SysRole> wrapper = new MPJLambdaWrapper<SysRole>()
            .selectAll(SysRole.class)
            .orderByDesc(SysRole::getCreateTime);
        return sysRoleMapper.selectJoinPage(new Page<>(page, size), SysRoleVo.class, wrapper);
    }


    public SysRoleVo save(SysRoleDTO dto) {
        SysRole entity = HutuUtils.transToObject(dto, SysRole.class);
        entity.setDelFlag(false);
        this.save(entity);
        return HutuUtils.transToObject(entity, SysRoleVo.class);
    }

    public SysRoleVo update(SysRoleDTO dto) {
        Long id = dto.getId();
        SysRole entity = this.getById(id);
        if(HutuUtils.isEmpty(entity)){
            throw new HutuBizException("Entity not found.");
        }
        HutuUtils.copyProperties(dto, entity);
        this.updateById(entity);
        return HutuUtils.transToObject(entity, SysRoleVo.class);
    }
}