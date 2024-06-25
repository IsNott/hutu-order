package org.nott.service.service;

import org.nott.dto.UserPackageAddDTO;
import org.nott.dto.UserPackageQueryDTO;
import org.nott.model.BizUserPackage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.nott.vo.UserPackageVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author nott
 * @since 2024-06-03
 */
public interface IBizUserPackageService extends IService<BizUserPackage> {

    List<UserPackageVo> queryPackageInfoByUserId();

    void packageAddItem(UserPackageAddDTO dto);
}
