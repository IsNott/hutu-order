package org.nott.service.mapper;

import org.apache.ibatis.annotations.Param;
import org.nott.model.BizPayOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.nott.vo.FrontOrderVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author nott
 * @since 2024-05-24
 */
public interface BizPayOrderMapper extends BaseMapper<BizPayOrder> {

    List<String> selectOrderMoney();

    FrontOrderVo orderFrontQueryByOrderId(@Param("settleTime") String settleTime,@Param("orderId") Long orderId);
}
