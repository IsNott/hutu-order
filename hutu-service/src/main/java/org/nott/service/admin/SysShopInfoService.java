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

import java.util.Date;

/**
* 门店信息表 Service
*/
@Service
public class SysShopInfoService extends ServiceImpl<SysShopInfoMapper, SysShopInfo>  {


    public IPage<SysShopInfoVo> queryPage(Integer page, Integer size, SysShopInfoDTO dto) {
        String address = dto.getAddress();
        Date createTime = dto.getCreateTime();
        String shopName = dto.getShopName();
        LambdaQueryWrapper<SysShopInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(HutuUtils.isNotEmpty(address), SysShopInfo::getAddress, address);
        wrapper.ge(HutuUtils.isNotEmpty(createTime), SysShopInfo::getCreateTime, createTime);
        wrapper.like(HutuUtils.isNotEmpty(shopName), SysShopInfo::getShopName, shopName);
        IPage<SysShopInfoVo> voPage = this.page(new Page<>(page, size), wrapper)
                .convert(item -> HutuUtils.transToObject(item, SysShopInfoVo.class));
        return voPage;
    }
}