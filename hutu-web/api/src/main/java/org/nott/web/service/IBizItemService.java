package org.nott.web.service;

import org.nott.model.BizItem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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
}
