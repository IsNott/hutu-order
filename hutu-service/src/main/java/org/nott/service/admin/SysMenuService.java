package org.nott.service.admin;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.nott.model.SysMenu;
import org.nott.service.mapper.admin.SysMenuMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.nott.vo.SysMenuTreeNodeVo;
import org.springframework.stereotype.Service;
import org.nott.dto.SysMenuDTO;
import org.nott.vo.SysMenuVo;
import org.nott.common.utils.HutuUtils;
import org.nott.common.exception.HutuBizException;
import javax.annotation.Resource;
import java.util.List;

/**
* 系统菜单表 Service
*/
@Service
public class SysMenuService extends ServiceImpl<SysMenuMapper, SysMenu>  {

    @Resource
    private SysMenuMapper sysMenuMapper;

    public IPage<SysMenuVo> queryPage(Integer page, Integer size, SysMenuDTO dto) {
        MPJLambdaWrapper<SysMenu> wrapper = new MPJLambdaWrapper<SysMenu>()
            .selectAll(SysMenu.class)
            .orderByAsc(SysMenu::getSort);
        return sysMenuMapper.selectJoinPage(new Page<>(page, size), SysMenuVo.class, wrapper);
    }

    public IPage<SysMenuTreeNodeVo> treeNodePage(Integer page, Integer size, SysMenuDTO dto) {
        Long parentId = dto.getParentId();
        Integer type = dto.getType();
        MPJLambdaWrapper<SysMenu> wrapper = new MPJLambdaWrapper<SysMenu>()
            .selectAll(SysMenu.class)
            .eq(SysMenu::getParentId, parentId)
            .eq(HutuUtils.isNotEmpty(type), SysMenu::getType, type)
            .orderByAsc(SysMenu::getSort);
        Page<SysMenuTreeNodeVo> nodeVoPage = sysMenuMapper.selectJoinPage(new Page<>(page, size), SysMenuTreeNodeVo.class, wrapper);
        if(HutuUtils.isEmpty(nodeVoPage) || HutuUtils.isEmpty(nodeVoPage.getRecords())){
            return nodeVoPage;
        }
        List<SysMenuTreeNodeVo> records = nodeVoPage.getRecords();
        for (SysMenuTreeNodeVo record : records) {
            // 递归查询子节点
            setChildNodes(record, dto);
        }
        return nodeVoPage;
    }

    private void setChildNodes(SysMenuTreeNodeVo record, SysMenuDTO dto) {
        Long id = record.getId();
        Integer type = dto.getType();
        MPJLambdaWrapper<SysMenu> wrapper = new MPJLambdaWrapper<SysMenu>()
            .selectAll(SysMenu.class)
            .eq(SysMenu::getParentId, id)
            .eq(HutuUtils.isNotEmpty(type), SysMenu::getType, type)
            .orderByDesc(SysMenu::getCreateTime);
        List<SysMenuTreeNodeVo> childNodes = sysMenuMapper.selectJoinList(SysMenuTreeNodeVo.class, wrapper);
        if(HutuUtils.isNotEmpty(childNodes)){
            for (SysMenuTreeNodeVo childNode : childNodes) {
                setChildNodes(childNode, dto);
            }
            record.setChildren(childNodes);
        }
    }


    public SysMenuVo save(SysMenuDTO dto) {
        SysMenu entity = HutuUtils.transToObject(dto, SysMenu.class);
        entity.setDelFlag(false);
        this.save(entity);
        return HutuUtils.transToObject(entity, SysMenuVo.class);
    }

    public SysMenuVo update(SysMenuDTO dto) {
        Long id = dto.getId();
        SysMenu entity = this.getById(id);
        if(HutuUtils.isEmpty(entity)){
            throw new HutuBizException("Entity not found.");
        }
        HutuUtils.copyProperties(dto, entity);
        this.updateById(entity);
        return HutuUtils.transToObject(entity, SysMenuVo.class);
    }

    public void deleteNode(Long id) {
        SysMenu entity = this.getById(id);
        if(HutuUtils.isEmpty(entity)){
            throw new HutuBizException("Entity not found.");
        }
        SysMenuTreeNodeVo sysMenuTreeNodeVo = HutuUtils.transToObject(entity, SysMenuTreeNodeVo.class);
        this.setChildNodes(sysMenuTreeNodeVo, new SysMenuDTO());
        // 递归删除
        deleteRecursively(sysMenuTreeNodeVo);
    }

    private void deleteRecursively(SysMenuTreeNodeVo sysMenuTreeNodeVo) {
        List<SysMenuTreeNodeVo> children = sysMenuTreeNodeVo.getChildren();
        if(HutuUtils.isNotEmpty(children)){
            for (SysMenuTreeNodeVo child : children) {
                deleteRecursively(child);
            }
        }
        this.removeById(sysMenuTreeNodeVo.getId());
    }

    public void updateParent(List<Long> ids, Long newParentId) {
        for (Long id : ids) {
            SysMenu entity = this.getById(id);
            if(HutuUtils.isEmpty(entity)){
                throw new HutuBizException("Entity not found for id: " + id);
            }
            entity.setParentId(newParentId);
            this.updateById(entity);
        }
    }
}