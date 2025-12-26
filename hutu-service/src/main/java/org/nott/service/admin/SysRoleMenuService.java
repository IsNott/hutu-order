package org.nott.service.admin;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.nott.model.SysRoleMenu;
import org.nott.service.mapper.admin.SysRoleMenuMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import org.nott.dto.SysRoleMenuDTO;
import org.nott.vo.SysRoleMenuVo;
import org.nott.common.utils.HutuUtils;
import org.nott.common.exception.HutuBizException;
import javax.annotation.Resource;
/**
* 角色-菜单权限表 Service
*/
@Service
public class SysRoleMenuService extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu>  {

    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;

    public IPage<SysRoleMenuVo> queryPage(Integer page, Integer size, SysRoleMenuDTO dto) {
        MPJLambdaWrapper<SysRoleMenu> wrapper = new MPJLambdaWrapper<SysRoleMenu>()
            .selectAll(SysRoleMenu.class)
            .orderByDesc(SysRoleMenu::getCreateTime);
        return sysRoleMenuMapper.selectJoinPage(new Page<>(page, size), SysRoleMenuVo.class, wrapper);
    }


    public SysRoleMenuVo save(SysRoleMenuDTO dto) {
        SysRoleMenu entity = HutuUtils.transToObject(dto, SysRoleMenu.class);
        entity.setDelFlag(false);
        this.save(entity);
        return HutuUtils.transToObject(entity, SysRoleMenuVo.class);
    }

    public SysRoleMenuVo update(SysRoleMenuDTO dto) {
        Long id = dto.getId();
        SysRoleMenu entity = this.getById(id);
        if(HutuUtils.isEmpty(entity)){
            throw new HutuBizException("Entity not found.");
        }
        HutuUtils.copyProperties(dto, entity);
        this.updateById(entity);
        return HutuUtils.transToObject(entity, SysRoleMenuVo.class);
    }
}