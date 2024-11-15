package org.nott.service.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.nott.dto.UserPackageAddDTO;
import org.nott.dto.UserPackageQueryDTO;
import org.nott.model.BizUserPackage;
import org.nott.vo.UserPackageVo;
import org.nott.service.mapper.BizUserPackageMapper;
import org.nott.service.service.IBizUserPackageService;
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
        return bizUserPackageMapper.selectUserPackageByUserId(userId);
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
}
