package org.nott.service.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.nott.common.exception.HutuBizException;
import org.nott.dto.SysMenuCatalogCopyDTO;
import org.nott.dto.SysMenuCatalogDTO;
import org.nott.dto.UserPackageQueryDTO;
import org.nott.model.SysMenuCatalog;
import org.nott.model.SysShopInfo;
import org.nott.service.mapper.admin.SysMenuCatalogMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.nott.vo.PayOrderVo;
import org.nott.vo.SysMenuCatalogVo;
import org.springframework.stereotype.Service;
import org.nott.common.utils.HutuUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* 门店菜单分类表 Service
*/
@Service
public class SysMenuCatalogService extends ServiceImpl<SysMenuCatalogMapper, SysMenuCatalog>  {

    @Resource
    private SysMenuCatalogMapper sysMenuCatalogMapper;

    public IPage<SysMenuCatalogVo> queryPage(Integer page, Integer size, SysMenuCatalogDTO dto) {
        Long shopId = dto.getShopId();
        MPJLambdaWrapper<SysMenuCatalog> wrapper = new MPJLambdaWrapper<SysMenuCatalog>()
                .selectAll(SysMenuCatalog.class)
                .select(SysShopInfo::getShopName)
                .leftJoin(SysShopInfo.class, SysShopInfo::getId, SysMenuCatalog::getShopId)
                .eq(shopId != null, SysMenuCatalog::getShopId, shopId)
                .orderByAsc(SysMenuCatalog::getShowIndex);
        return sysMenuCatalogMapper.selectJoinPage(new Page<>(page, size), SysMenuCatalogVo.class, wrapper);
    }


    public SysMenuCatalogVo save(SysMenuCatalogDTO dto) {
        SysMenuCatalog entity = HutuUtils.transToObject(dto, SysMenuCatalog.class);
        entity.setDelFlag(false);
        this.save(entity);
        return HutuUtils.transToObject(entity, SysMenuCatalogVo.class);
    }

    public SysMenuCatalogVo update(SysMenuCatalogDTO dto) {
        Long id = dto.getId();
        SysMenuCatalog entity = this.getById(id);
        if(HutuUtils.isEmpty(entity)){
            throw new HutuBizException("Entity not found.");
        }
        HutuUtils.copyProperties(dto, entity);
        this.updateById(entity);
        return HutuUtils.transToObject(entity, SysMenuCatalogVo.class);
    }

    public void copy2Shops(SysMenuCatalogCopyDTO dto) {
        List<Long> shopIds = dto.getShopIds();
        List<Long> menuCatalogIds = dto.getMenuCatalogIds();

        LambdaQueryWrapper<SysMenuCatalog> wrapper = new LambdaQueryWrapper<SysMenuCatalog>().in(SysMenuCatalog::getId, menuCatalogIds);
        List<SysMenuCatalog> sysMenuCatalogs = this.list(wrapper);

        MPJLambdaWrapper<SysMenuCatalog> catalogMPJLambdaWrapper = new MPJLambdaWrapper<SysMenuCatalog>()
                .selectAll(SysMenuCatalog.class)
                .select(SysShopInfo::getShopName)
                .leftJoin(SysShopInfo.class, SysShopInfo::getId, SysMenuCatalog::getShopId)
                .in(SysMenuCatalog::getShopId, shopIds);
        List<SysMenuCatalogVo> shopExistCatalogVos = sysMenuCatalogMapper.selectJoinList(SysMenuCatalogVo.class, catalogMPJLambdaWrapper);

        Map<Long, List<SysMenuCatalogVo>> groupMap = shopExistCatalogVos.stream().collect(Collectors.groupingBy(
                SysMenuCatalogVo::getShopId
        ));
        for(Long shopId : shopIds){
            List<SysMenuCatalogVo> existCatalogs = groupMap.get(shopId);
            for(Long menuCatalogId : menuCatalogIds){
                // id校验
                SysMenuCatalog sysMenuCatalog = sysMenuCatalogs.stream().filter(item -> item.getId().equals(menuCatalogId)).findFirst().orElse(null);
                if(HutuUtils.isEmpty(sysMenuCatalog)){
                    continue;
                }
                // 重复校验
                if(!HutuUtils.isEmpty(existCatalogs)){
                    boolean present = existCatalogs.stream().anyMatch(item -> item.getMenuCatalogName().equals(sysMenuCatalog.getMenuCatalogName()));
                    if(present) continue;
                }
                SysMenuCatalog newCatalog = HutuUtils.transToObject(sysMenuCatalog, SysMenuCatalog.class);
                newCatalog.setId(null);
                newCatalog.setShopId(shopId);
                this.save(newCatalog);
            }
        }
    }
}