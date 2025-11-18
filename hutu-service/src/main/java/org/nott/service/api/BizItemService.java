package org.nott.service.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.nott.common.redis.RedisUtils;
import org.nott.model.BizItem;
import org.nott.service.mapper.api.BizItemMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author nott
 * @since 2024-05-24
 */
@Service
public class BizItemService extends ServiceImpl<BizItemMapper, BizItem>  {

    @Resource
    private RedisUtils redisUtils;

     
    public List<BizItem> searchItemByName(String keyWord) {
        LambdaQueryWrapper<BizItem> queryWrapper = new LambdaQueryWrapper<BizItem>()
                .like(BizItem::getItemName, keyWord);
        List<BizItem> bizItems = this.list(queryWrapper);
        return bizItems;
    }

     
    public Set<String> getSearchItemTags() {
        Set<String> bizItemTags = (Set)redisUtils.sGet("bizItemTags");
        return bizItemTags;
    }
}
