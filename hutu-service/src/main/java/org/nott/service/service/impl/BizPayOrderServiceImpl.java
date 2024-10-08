package org.nott.service.service.impl;

import org.nott.dto.UserSettleOrderDTO;
import org.nott.model.BizPayOrder;
import org.nott.service.mapper.BizPayOrderMapper;
import org.nott.service.service.IBizPayOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.nott.vo.SettleOrderVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author nott
 * @since 2024-05-24
 */
@Service
public class BizPayOrderServiceImpl extends ServiceImpl<BizPayOrderMapper, BizPayOrder> implements IBizPayOrderService {

    @Resource
    private BizPayOrderMapper bizPayOrderMapper;

    @Override
    public String countPurchases() {
        BigDecimal decimal = new BigDecimal("0.0");
        List<String> money = bizPayOrderMapper.selectOrderMoney();
        if(!money.isEmpty()){
            for (String singlePay : money) {
                BigDecimal single = new BigDecimal(singlePay);
                decimal = decimal.add(single);
            }
        }
        return decimal.toString();
    }

    @Override
    public SettleOrderVo doUserSettle(UserSettleOrderDTO userSettleOrderDTO) {
        SettleOrderVo vo = new SettleOrderVo();
        // TODO 检验积分
        // TODO 检验优惠券
        // TODO 检验库存
        // TODO 确认需支付金额

        return vo;
    }
}
