package org.nott.service.service;

import org.nott.dto.PayWayQueryDTO;
import org.nott.model.BizPayWay;
import com.baomidou.mybatisplus.extension.service.IService;
import org.nott.vo.PayWayVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author nott
 * @since 2024-05-24
 */
public interface IBizPayWayService extends IService<BizPayWay> {

    List<PayWayVo> listPayWay(PayWayQueryDTO dto);
}
