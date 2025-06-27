package org.nott.service.api.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.nott.common.config.BusinessConfig;
import org.nott.common.redis.RedisUtils;
import org.nott.common.utils.HutuUtils;
import org.nott.common.utils.SpringContextUtil;
import org.nott.model.BizBusinessConfig;
import org.nott.service.api.IBizBusinessConfigService;
import org.nott.service.mapper.api.BizBusinessConfigMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

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

    @PostConstruct
    public void init() {
        // 初始化业务配置
        BusinessConfig bean = SpringContextUtil.getBean(BusinessConfig.class);
        if(HutuUtils.isNotEmpty(bean)){
            redisUtils.set(RedisUtils.Keys.BUSINESS_CONFIG_KEY, JSON.toJSONString(bean));
        }
    }

    @Resource
    private RedisUtils redisUtils;

    @Override
    public BizBusinessConfig getByBizKey(String bizKey) {
        return this.getOne(new QueryWrapper<BizBusinessConfig>().eq("biz_key", bizKey));
    }

    @Override
    public void deleteByBizKey(String bizKey) {
        this.remove(new QueryWrapper<BizBusinessConfig>().eq("biz_key", bizKey));
    }

    @Override
    public BusinessConfig getRedisConfig() {
        BusinessConfig businessConfig = redisUtils.get(RedisUtils.Keys.BUSINESS_CONFIG_KEY, BusinessConfig.class);
        return businessConfig;
    }
}
