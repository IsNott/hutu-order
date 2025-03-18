package org.nott.service.api;

import org.nott.model.BizItem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author nott
 * @since 2024-05-24
 */
public interface IBizItemService extends IService<BizItem> {

    List<BizItem> searchItemByName(String keyWord);

    Set<String> getSearchItemTags();
}
