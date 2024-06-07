package org.nott.admin.service.impl;

import org.nott.model.BizItem;
import org.nott.admin.mapper.SysBizItemMapper;
import org.nott.admin.service.ISysBizItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author nott
 * @since 2024-06-07
 */
@Service
public class SysBizItemServiceImpl extends ServiceImpl<SysBizItemMapper, BizItem> implements ISysBizItemService {

}
