package org.nott.service.api;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import lombok.extern.slf4j.Slf4j;

import org.nott.common.config.BusinessConfig;
import org.nott.common.redis.RedisUtils;
import org.nott.common.utils.HutuUtils;
import org.nott.common.utils.SpringContextUtil;
import org.nott.model.BizBusinessConfig;
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
@Slf4j
public class BizBusinessConfigService extends ServiceImpl<BizBusinessConfigMapper, BizBusinessConfig> {

    @PostConstruct
    public void init() {
        log.info("初始化业务配置");
        // 初始化业务配置
        BusinessConfig bean = SpringContextUtil.getBean(BusinessConfig.class);
        if(HutuUtils.isNotEmpty(bean)){
            redisUtils.set(RedisUtils.Keys.BUSINESS_CONFIG_KEY, JSON.toJSONString(bean));
        }
    }

    @Resource
    private RedisUtils redisUtils;

     
    public BizBusinessConfig getByBizKey(String bizKey) {
        return this.getOne(new QueryWrapper<BizBusinessConfig>().eq("biz_key", bizKey));
    }

     
    public void deleteByBizKey(String bizKey) {
        this.remove(new QueryWrapper<BizBusinessConfig>().eq("biz_key", bizKey));
    }

     
    public BusinessConfig getRedisConfig() {
        BusinessConfig businessConfig = redisUtils.get(RedisUtils.Keys.BUSINESS_CONFIG_KEY, BusinessConfig.class);
        return businessConfig;
    }
}
