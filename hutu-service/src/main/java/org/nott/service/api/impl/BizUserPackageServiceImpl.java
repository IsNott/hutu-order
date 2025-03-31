package org.nott.service.api.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.nott.common.ResponseEntity;
import org.nott.common.exception.HutuBizException;
import org.nott.common.utils.HutuUtils;
import org.nott.common.utils.SpringContextUtil;
import org.nott.dto.UserPackageAddDTO;
import org.nott.dto.UserPackageUpDateDTO;
import org.nott.feign.OssClient;
import org.nott.model.BizUserPackage;
import org.nott.vo.OssFileVo;
import org.nott.vo.UserPackageVo;
import org.nott.service.mapper.api.BizUserPackageMapper;
import org.nott.service.api.IBizUserPackageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author nott
 * @since 2024-06-03
 */
@Service
public class BizUserPackageServiceImpl extends ServiceImpl<BizUserPackageMapper, BizUserPackage> implements IBizUserPackageService {

    @Resource
    private BizUserPackageMapper bizUserPackageMapper;

    @Override
    public List<UserPackageVo> queryPackageInfoByUserId() {
        Long userId = Long.parseLong((String)StpUtil.getLoginId());
        return this.queryPackageInfoByUserId(userId);
    }

    @Override
    public List<UserPackageVo> queryPackageInfoByUserId(Long userId) {
        List<UserPackageVo> userPackageVos = bizUserPackageMapper.selectUserPackageByUserId(userId);
        if(HutuUtils.isNotEmpty(userPackageVos)){
            OssClient ossClient = SpringContextUtil.getBean(OssClient.class);
            ResponseEntity<List<OssFileVo>> ossFile = ossClient.getByBizId(userPackageVos.stream().map(UserPackageVo::getItemId).collect(Collectors.toList()));
            if (ossFile.getCode() != 200){
                throw new HutuBizException(ossFile.getMessage());
            }
            List<OssFileVo> data = ossFile.getData();
            userPackageVos.forEach(userPackageVo -> {
                List<OssFileVo> files = data.stream().filter(ossFileVo -> userPackageVo.getItemId().equals(ossFileVo.getBizId())).collect(Collectors.toList());
                userPackageVo.setItemImageUrls(files.stream().map(OssFileVo::getPath).collect(Collectors.toList()));
            });
        }
        return userPackageVos;
    }

    @Override
    public void packageAddItem(UserPackageAddDTO dto) {
        Long userId = Long.parseLong((String)StpUtil.getLoginId());
        Long itemId = dto.getItemId();
        Integer itemPiece = dto.getItemPiece();
        String skuItemContents = dto.getSkuItemContents();

        BizUserPackage userPackage = new BizUserPackage();
        userPackage.setUserId(userId);
        userPackage.setItemId(itemId);
        userPackage.setItemPiece(itemPiece);
        userPackage.setSkuItemContents(skuItemContents);

        this.save(userPackage);
    }

    @Override
    public void cancelAddPackage() {
        long userId = StpUtil.getLoginIdAsLong();
        bizUserPackageMapper.removeByUserId(userId);
    }

    @Override
    public Long queryPackageNumByUserId() {
        long userId = StpUtil.getLoginIdAsLong();
        LambdaUpdateWrapper<BizUserPackage> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(BizUserPackage::getUserId,userId);
        long count = this.count(wrapper);
        return count;
    }

    @Override
    public UserPackageVo updateContext(UserPackageUpDateDTO dto) {
        BizUserPackage bizUserPackage = HutuUtils.transToObject(dto, BizUserPackage.class);
        this.updateById(bizUserPackage);
        return HutuUtils.transToObject(dto, UserPackageVo.class);
    }
}
