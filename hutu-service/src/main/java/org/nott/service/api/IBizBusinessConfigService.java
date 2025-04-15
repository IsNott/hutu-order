package org.nott.service.api;

import org.nott.model.BizBusinessConfig;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author nott
 * @since 2025-04-15
 */
public interface IBizBusinessConfigService extends IService<BizBusinessConfig> {

    /**
     * 根据业务key查询业务配置
     *
     * @param bizKey 业务key
     * @return 业务配置
     */
    BizBusinessConfig getByBizKey(String bizKey);

    /**
     * 根据业务key删除业务配置
     *
     * @param bizKey 业务key
     */
    void deleteByBizKey(String bizKey);

}
