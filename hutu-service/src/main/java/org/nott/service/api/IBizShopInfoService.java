package org.nott.service.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.nott.dto.SysShopPageDTO;
import org.nott.model.BizShopInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.nott.vo.ShopInfoVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author nott
 * @since 2024-06-26
 */
public interface IBizShopInfoService extends IService<BizShopInfo> {

    List<ShopInfoVo> listShopInfo();

    ShopInfoVo getDefaultShop();

    List<ShopInfoVo> searchShopByKeyWord(String keyWord);

    ShopInfoVo getShopById(Long id);

    Page<ShopInfoVo> page(SysShopPageDTO dto, int page, int size);
}
