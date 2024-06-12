package org.nott.service.service;

import org.nott.model.BizItemSkuRelation;
import com.baomidou.mybatisplus.extension.service.IService;
import org.nott.vo.ItemSkuVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author nott
 * @since 2024-06-03
 */
public interface IBizItemSkuRelationService extends IService<BizItemSkuRelation> {

    List<ItemSkuVo> selectItemSkuList(Long itemId);
}
