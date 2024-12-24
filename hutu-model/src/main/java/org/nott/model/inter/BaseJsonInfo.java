package org.nott.model.inter;

import com.alibaba.fastjson.JSON;

/**
 * @author Nott
 * @date 2024-12-24
 */
public abstract class BaseJsonInfo {

    public String toJSONString(){
        return JSON.toJSONString(this);
    }

}
