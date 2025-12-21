package org.nott.service.admin;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.nott.common.exception.HutuBizException;
import org.nott.dto.SysMenuCatalogDTO;
import org.nott.dto.UserPackageQueryDTO;
import org.nott.model.SysMenuCatalog;
import org.nott.model.SysShopInfo;
import org.nott.service.mapper.admin.SysMenuCatalogMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.nott.vo.PayOrderVo;
import org.springframework.stereotype.Service;
import org.nott.common.utils.HutuUtils;

import javax.annotation.Resource;

/**
* 门店菜单分类表 Service
*/
@Service
public class SysMenuCatalogService extends ServiceImpl<SysMenuCatalogMapper, SysMenuCatalog>  {

    @Resource
    private SysMenuCatalogMapper sysMenuCatalogMapper;

    public IPage<PayOrderVo.SysMenuCatalogVo> queryPage(Integer page, Integer size, SysMenuCatalogDTO dto) {
        Long shopId = dto.getShopId();
        MPJLambdaWrapper<SysMenuCatalog> wrapper = new MPJLambdaWrapper<SysMenuCatalog>()
                .selectAll(SysMenuCatalog.class)
                .select(SysShopInfo::getShopName)
                .leftJoin(SysShopInfo.class, SysShopInfo::getId, SysMenuCatalog::getShopId)
                .eq(shopId != null, SysMenuCatalog::getShopId, shopId)
                .orderByAsc(SysMenuCatalog::getShowIndex);
        return sysMenuCatalogMapper.selectJoinPage(new Page<>(page, size), PayOrderVo.SysMenuCatalogVo.class, wrapper);
    }


    public PayOrderVo.SysMenuCatalogVo save(SysMenuCatalogDTO dto) {
        SysMenuCatalog entity = HutuUtils.transToObject(dto, SysMenuCatalog.class);
        entity.setDelFlag(false);
        this.save(entity);
        return HutuUtils.transToObject(entity, PayOrderVo.SysMenuCatalogVo.class);
    }

    public PayOrderVo.SysMenuCatalogVo update(SysMenuCatalogDTO dto) {
        Long id = dto.getId();
        SysMenuCatalog entity = this.getById(id);
        if(HutuUtils.isEmpty(entity)){
            throw new HutuBizException("Entity not found.");
        }
        HutuUtils.copyProperties(dto, entity);
        this.updateById(entity);
        return HutuUtils.transToObject(entity, PayOrderVo.SysMenuCatalogVo.class);
    }
}