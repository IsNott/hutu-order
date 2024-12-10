package org.nott.service.service;

import org.nott.dto.UserSettleOrderDTO;
import org.nott.model.BizPayOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import org.nott.vo.FrontOrderVo;
import org.nott.vo.PayOrderVo;
import org.nott.vo.SettleOrderVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author nott
 * @since 2024-05-24
 */
public interface IBizPayOrderService extends IService<BizPayOrder> {

    String countPurchases();

    SettleOrderVo doUserSettle(UserSettleOrderDTO userSettleOrderDTO);

    PayOrderVo queryPayOrderById(Long id);

    PayOrderVo orderQuery(Long id);

    FrontOrderVo orderFront(Long orderId);

    void simulateNotify(Long orderId);
}
