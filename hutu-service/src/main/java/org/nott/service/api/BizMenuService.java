package org.nott.service.api;

import org.nott.feign.OssClient;
import org.nott.model.BizMenu;
import org.nott.vo.MenuItemVo;
import org.nott.service.mapper.api.BizMenuMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.nott.vo.OssFileVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author nott
 * @since 2024-05-24
 */
@Service
public class BizMenuService extends ServiceImpl<BizMenuMapper, BizMenu> {

    @Resource
    private BizMenuMapper bizMenuMapper;
    @Resource
    private OssClient ossFileService;

     
    public List<MenuItemVo> getByCatalogId(String catalogId) {
        List<MenuItemVo> itemVoList = bizMenuMapper.getMenuItemListByCatalogId(catalogId);
        setImgs(itemVoList);
        return itemVoList;
    }

    private void setImgs(List<MenuItemVo> itemVoList) {
        List<Long> ids = itemVoList.stream().map(MenuItemVo::getItemId).collect(Collectors.toList());
        List<OssFileVo> fileVos = ossFileService.getByBizId(ids).getData();
        itemVoList.forEach(itemVo -> {
            List<OssFileVo> fileVoList = fileVos.stream().filter(fileVo -> fileVo.getBizId().equals(itemVo.getItemId())).collect(Collectors.toList());
            itemVo.setItemImage(fileVoList.stream().map(OssFileVo::getPath).collect(Collectors.toList()));
        });
    }

     
    public List<MenuItemVo> getByShopCatalogId(Long shopId,String catalogId) {
        List<MenuItemVo> itemVoList = bizMenuMapper.getMenuItemListByShopCatalogId(shopId,catalogId);
        this.setImgs(itemVoList);
        return itemVoList;
    }
}
