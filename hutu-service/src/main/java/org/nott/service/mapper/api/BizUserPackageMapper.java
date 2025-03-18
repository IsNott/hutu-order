package org.nott.service.mapper.api;

import org.nott.model.BizUserPackage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.nott.vo.UserPackageVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author nott
 * @since 2024-06-03
 */
public interface BizUserPackageMapper extends BaseMapper<BizUserPackage> {

    List<UserPackageVo> selectUserPackageByUserId(Long userId);

    void removeByUserId(long userId);
}
