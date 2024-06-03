package org.nott.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.nott.dto.UserPackageAddDTO;
import org.nott.dto.UserPackageQueryDTO;
import org.nott.model.BizUserPackage;
import org.nott.vo.UserPackageVo;
import org.nott.web.mapper.BizUserPackageMapper;
import org.nott.web.service.IBizUserPackageService;
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
    public List<UserPackageVo> queryPackageInfoByUserId(UserPackageQueryDTO dto) {
        Long userId = dto.getUserId();
        List<UserPackageVo> userPackageVos = bizUserPackageMapper.selectUserPackageByUserId(userId);
        return userPackageVos;
    }

    @Override
    public void packageAddItem(UserPackageAddDTO dto) {
        Long userId = dto.getUserId();
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
}
