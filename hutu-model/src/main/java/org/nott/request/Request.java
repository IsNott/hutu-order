package org.nott.request;

import lombok.extern.slf4j.Slf4j;
import org.nott.common.utils.HutuUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nott
 * @date 2025-12
 */
@Slf4j
public abstract class Request<DTO> {

    public DTO toDTO() {
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            if (actualTypeArguments.length > 0) {
                DTO dto = HutuUtils.transToObject(this, (Class<DTO>) actualTypeArguments[0]);

                // 递归转换所有 List<Request> 类型的字段
                convertListFieldsToDTO(this, dto);

                return dto;
            }
        } else {
            throw new IllegalArgumentException("Missing type parameter.");
        }
        return null;
    }

    private void convertListFieldsToDTO(Object source, Object target) {
        if (source == null || target == null) return;

        // 获取源对象的所有字段
        java.lang.reflect.Field[] fields = source.getClass().getDeclaredFields();

        for (java.lang.reflect.Field field : fields) {
            // 检查字段类型是否为 List<?>
            if (List.class.isAssignableFrom(field.getType())) {
                try {
                    field.setAccessible(true);
                    List<?> list = (List<?>) field.get(source);
                    if (list != null && !list.isEmpty()) {
                        // 判断 List 中的元素是否为 Request 类型
                        if (list.get(0) instanceof Request) {
                            // 递归转换每个 Request 元素
                            List<Object> dtoList = new ArrayList<>();
                            for (Object item : list) {
                                if (item instanceof Request) {
                                    Object dtoItem = ((Request<?>) item).toDTO();
                                    dtoList.add(dtoItem);
                                }
                            }
                            // 设置到目标对象的对应字段
                            java.lang.reflect.Field targetField = target.getClass().getDeclaredField(field.getName());
                            targetField.setAccessible(true);
                            targetField.set(target, dtoList);
                        }
                    }
                } catch (Exception e) {
                    // 日志记录或处理异常
                    log.error("Failed to convert field: " + field.getName(), e);
                }
            }
        }
    }
}
