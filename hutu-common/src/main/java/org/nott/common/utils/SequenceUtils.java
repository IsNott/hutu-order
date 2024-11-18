package org.nott.common.utils;

import org.nott.common.config.BusinessConfig;
import org.nott.common.redis.RedisUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Nott
 * @date 2024-11-18
 */
@Component
public class SequenceUtils {

    @Resource
    private RedisUtils redisUtils;
    @Resource
    private BusinessConfig businessConfig;

    public String nextSeq(String prefix){
        Long increment = redisUtils.getIncrement(prefix);
        String str = String.valueOf(increment);
        int len = str.length();
        if (len >= businessConfig.getOrderLength()) {
            return str;
        }
        int rest = businessConfig.getOrderLength() - len;
        StringBuilder sb = new StringBuilder(prefix);
        for (int i = 0; i < rest; i++) {
            sb.append('0');
        }
        sb.append(str);
        return sb.toString();
    }
}
