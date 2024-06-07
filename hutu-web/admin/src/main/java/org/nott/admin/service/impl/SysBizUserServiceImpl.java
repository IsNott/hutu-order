package org.nott.admin.service.impl;

import org.nott.model.BizUser;
import org.nott.admin.mapper.SysBizUserMapper;
import org.nott.admin.service.ISysBizUserService;
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
public class SysBizUserServiceImpl extends ServiceImpl<SysBizUserMapper, BizUser> implements ISysBizUserService {

}
