package org.nott.vo;

import lombok.Data;
import org.nott.model.BizShopInfo;

/**
 * @author Nott
 * @date 2024-7-2
 */

@Data
public class ShopInfoVo extends BizShopInfo {

    private boolean isOpen;
}
