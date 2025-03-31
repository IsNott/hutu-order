package org.nott.service.pay.impl;

import com.alibaba.fastjson.JSONObject;
import org.nott.common.annotation.PayApi;
import org.nott.dto.PayDTO;
import org.nott.dto.RefundDTO;
import org.nott.service.pay.PayService;
import org.nott.vo.PayResultVo;
import org.nott.vo.RefundResultVo;
import org.springframework.stereotype.Service;

/**
 * @author Nott
 * @date 2025-3-28
 */

@Service
@PayApi("P001")
public class BalancePayService implements PayService {

    @Override
    public PayResultVo doPay(PayDTO payDTO) {
        return null;
    }

    @Override
    public RefundResultVo doRefund(RefundDTO refundDTO) {
        return null;
    }

    @Override
    public void notify(JSONObject obj) {

    }
}
