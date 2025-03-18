package org.nott.service.api.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.nott.common.redis.RedisUtils;
import org.nott.model.BizItem;
import org.nott.service.mapper.api.BizItemMapper;
import org.nott.service.api.IBizItemService;
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
public class BizItemServiceImpl extends ServiceImpl<BizItemMapper, BizItem> implements IBizItemService {

    @Resource
    private RedisUtils redisUtils;

    @Override
    public List<BizItem> searchItemByName(String keyWord) {
        LambdaQueryWrapper<BizItem> queryWrapper = new LambdaQueryWrapper<BizItem>()
                .like(BizItem::getItemName, keyWord);
        List<BizItem> bizItems = this.list(queryWrapper);
        return bizItems;
    }

    @Override
    public Set<String> getSearchItemTags() {
        Set<String> bizItemTags = (Set)redisUtils.sGet("bizItemTags");
        return bizItemTags;
    }
}
