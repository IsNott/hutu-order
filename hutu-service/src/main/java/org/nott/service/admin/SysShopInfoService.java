package org.nott.service.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.nott.common.utils.HutuUtils;
import org.nott.dto.SysShopInfoDTO;
import org.nott.model.SysShopInfo;
import org.nott.service.mapper.admin.SysShopInfoMapper;
import org.nott.vo.SysShopInfoVo;
import org.springframework.stereotype.Service;

/**
* 门店信息表 Service
*/
@Service
public class SysShopInfoService extends ServiceImpl<SysShopInfoMapper, SysShopInfo>  {


    public IPage<SysShopInfoVo> queryPage(Integer page, Integer size, SysShopInfoDTO dto) {
        LambdaQueryWrapper<SysShopInfo> wrapper = new LambdaQueryWrapper<>();
        IPage<SysShopInfoVo> voPage = this.page(new Page<>(page, size), wrapper)
                .convert(item -> HutuUtils.transToObject(item, SysShopInfoVo.class));
        return voPage;
    }
}