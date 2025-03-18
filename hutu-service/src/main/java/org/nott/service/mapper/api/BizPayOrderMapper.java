package org.nott.service.mapper.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.nott.model.BizPayOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.nott.vo.FrontOrderVo;
import org.nott.vo.MyPayOrderVo;

import javax.validation.constraints.NotNull;
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

    Page<MyPayOrderVo> queryOrderPageByUserId(Page<?> page, @NotNull @Param("id") long id, @Param("status") Integer status, @Param("keyWord") String keyWord);
}
