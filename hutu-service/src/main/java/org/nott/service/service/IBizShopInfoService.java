package org.nott.service.service;

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
}
