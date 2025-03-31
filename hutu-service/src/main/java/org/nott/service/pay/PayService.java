package org.nott.service.pay;

import com.alibaba.fastjson.JSONObject;
import org.nott.dto.PayDTO;
import org.nott.dto.RefundDTO;
import org.nott.vo.PayResultVo;
import org.nott.vo.RefundResultVo;

/**
 * @author Nott
 * @date 2025-3-28
 */
public interface PayService {

    PayResultVo doPay(PayDTO payDTO);

    RefundResultVo doRefund(RefundDTO refundDTO);

    void notify(JSONObject obj);
}
