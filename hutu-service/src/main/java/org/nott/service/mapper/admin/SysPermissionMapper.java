package org.nott.service.mapper.admin;

import org.nott.model.SysPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author nott
 * @since 2024-06-07
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    List<SysPermission> getPermissionByUserId(Object loginId);
}
