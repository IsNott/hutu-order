package org.nott.service.admin;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.nott.model.SysUserRole;
import org.nott.service.mapper.admin.SysUserRoleMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import org.nott.dto.SysUserRoleDTO;
import org.nott.vo.SysUserRoleVo;
import org.nott.common.utils.HutuUtils;
import org.nott.common.exception.HutuBizException;
import javax.annotation.Resource;
import java.util.List;

/**
* 系统用户-角色关系表 Service
*/
@Service
public class SysUserRoleService extends ServiceImpl<SysUserRoleMapper, SysUserRole>  {

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    public IPage<SysUserRoleVo> queryPage(Integer page, Integer size, SysUserRoleDTO dto) {
        MPJLambdaWrapper<SysUserRole> wrapper = new MPJLambdaWrapper<SysUserRole>()
            .selectAll(SysUserRole.class)
            .orderByDesc(SysUserRole::getCreateTime);
        return sysUserRoleMapper.selectJoinPage(new Page<>(page, size), SysUserRoleVo.class, wrapper);
    }


    public SysUserRoleVo save(SysUserRoleDTO dto) {
        SysUserRole entity = HutuUtils.transToObject(dto, SysUserRole.class);
        entity.setDelFlag(false);
        this.save(entity);
        return HutuUtils.transToObject(entity, SysUserRoleVo.class);
    }

    public SysUserRoleVo update(SysUserRoleDTO dto) {
        Long id = dto.getId();
        SysUserRole entity = this.getById(id);
        if(HutuUtils.isEmpty(entity)){
            throw new HutuBizException("Entity not found.");
        }
        HutuUtils.copyProperties(dto, entity);
        this.updateById(entity);
        return HutuUtils.transToObject(entity, SysUserRoleVo.class);
    }

    public void setRoleUsers(Long roleId, List<Long> userIds) {
        LambdaQueryWrapper<SysUserRole> wrapper = new LambdaQueryWrapper<SysUserRole>()
            .eq(SysUserRole::getRoleId, roleId);
        this.remove(wrapper);
        if(HutuUtils.isNotEmpty(userIds)){
            for (Long userId : userIds) {
                SysUserRole entity = new SysUserRole();
                entity.setRoleId(roleId);
                entity.setUserId(userId);
                entity.setDelFlag(false);
                this.save(entity);
            }
        }
    }
}