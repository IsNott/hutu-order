package org.nott.request;

import org.nott.common.utils.HutuUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author Nott
 * @date 2025-12
 */
public abstract class Request<DTO> {

    public DTO toDTO() {
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            if (actualTypeArguments.length > 0) {
                return HutuUtils.transToObject(this, (Class<DTO>) actualTypeArguments[0]);
            }
        } else {
            throw new IllegalArgumentException("Missing type parameter.");
        }
        return null;
    }
}
