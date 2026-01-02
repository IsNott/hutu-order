package org.nott.service.admin;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.nott.common.ResponseEntity;
import org.nott.dto.SysProductDTO;
import org.nott.feign.OssClient;
import org.nott.model.SysProduct;
import org.nott.service.mapper.admin.SysProductMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.nott.vo.OssFileVo;
import org.springframework.stereotype.Service;
import org.nott.vo.SysProductVo;
import org.nott.common.utils.HutuUtils;
import org.nott.common.exception.HutuBizException;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品表 Service
 */
@Service
public class SysProductService extends ServiceImpl<SysProductMapper, SysProduct> {

    @Resource
    private SysProductMapper sysProductMapper;
    @Resource
    private OssClient ossClient;

    public IPage<SysProductVo> queryPage(Integer page, Integer size, SysProductDTO dto) {
        String itemName = dto.getItemName();
        String itemDescription = dto.getItemDescription();
        MPJLambdaWrapper<SysProduct> wrapper = new MPJLambdaWrapper<SysProduct>()
                .selectAll(SysProduct.class)
                .like(HutuUtils.isNotEmpty(itemName), SysProduct::getItemName, itemName)
                .like(HutuUtils.isNotEmpty(itemDescription), SysProduct::getItemDescription, itemDescription)
                .orderByDesc(SysProduct::getCreateTime);
        Page<SysProductVo> voPage = sysProductMapper.selectJoinPage(new Page<>(page, size), SysProductVo.class, wrapper);
        return voPage;
    }


    public SysProductVo save(SysProductDTO dto) {
        SysProduct entity = HutuUtils.transToObject(dto, SysProduct.class);
        entity.setId(IdWorker.getId());
        entity.setDelFlag(false);
        this.save(entity);
        if(HutuUtils.isNotEmpty(dto.getImages())){
            ResponseEntity<Void> response = ossClient.relateOssFile(dto.getImages(), entity.getId());
            if (!response.isSuccess()) {
                throw new HutuBizException("Failed to associate images.");
            }
        }
        return HutuUtils.transToObject(entity, SysProductVo.class);
    }

    public SysProductVo update(SysProductDTO dto) {
        Long id = dto.getId();
        SysProduct entity = this.getById(id);
        if (HutuUtils.isEmpty(entity)) {
            throw new HutuBizException("Entity not found.");
        }
        HutuUtils.copyProperties(dto, entity);
        this.updateById(entity);
        if(HutuUtils.isNotEmpty(dto.getImages())){
            ResponseEntity<Void> response = ossClient.relateOssFile(dto.getImages(), entity.getId());
            if (!response.isSuccess()) {
                throw new HutuBizException("Failed to associate images.");
            }
        }
        return HutuUtils.transToObject(entity, SysProductVo.class);
    }

    public SysProductVo details(Long id) {
        SysProductVo vo = HutuUtils.transToObject(this.getById(id), SysProductVo.class);
        ResponseEntity<List<OssFileVo>> response = ossClient.getByBizId(vo.getId());
        if (response.isSuccess()) {
            vo.setImages(response.getData());
        }
        return vo;
    }
}