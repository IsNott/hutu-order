package org.nott.service.service;

import org.nott.model.BizMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import org.nott.vo.MenuItemVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author nott
 * @since 2024-05-24
 */
public interface IBizMenuService extends IService<BizMenu> {

    List<MenuItemVo> getByCatalogId(String catalogId);

}
