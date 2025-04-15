package org.nott.service.api.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.nott.model.BizBusinessConfig;
import org.nott.service.api.IBizBusinessConfigService;
import org.nott.service.mapper.api.BizBusinessConfigMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author nott
 * @since 2025-04-15
 */
@Service
public class BizBusinessConfigServiceImpl extends ServiceImpl<BizBusinessConfigMapper, BizBusinessConfig> implements IBizBusinessConfigService {

    @Override
    public BizBusinessConfig getByBizKey(String bizKey) {
        return this.getOne(new QueryWrapper<BizBusinessConfig>().eq("biz_key", bizKey));
    }

    @Override
    public void deleteByBizKey(String bizKey) {
        this.remove(new QueryWrapper<BizBusinessConfig>().eq("biz_key", bizKey));
    }
}
