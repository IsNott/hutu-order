package org.nott.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.nott.model.BizItem;
import org.nott.service.mapper.BizItemMapper;
import org.nott.service.service.IBizItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
    private BizItemMapper bizItemMapper;

    @Override
    public List<BizItem> searchItemByName(String keyWord) {
        LambdaQueryWrapper<BizItem> queryWrapper = new LambdaQueryWrapper<BizItem>()
                .like(BizItem::getItemName, keyWord);
        List<BizItem> bizItems = this.list(queryWrapper);
        return bizItems;
    }
}
