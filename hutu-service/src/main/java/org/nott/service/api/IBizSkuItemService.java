package org.nott.service.api;

import org.nott.model.BizSkuItem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author nott
 * @since 2024-06-03
 */
public interface IBizSkuItemService extends IService<BizSkuItem> {

    Map<Long,List<BizSkuItem>> queryByCatalogIds(List<Long> ids);

}
