package org.nott.enums;

import lombok.Getter;

/**
 * 基础字段枚举类
 */


@Getter
public enum BaseField {
    ID("id", "主键ID"),
    CREATE_TIME("createTime", "创建时间"),
    UPDATE_TIME("updateTime", "更新时间"),
    DELETE_FLAG("delFlag", "删除标志");

    private final String fieldName;
    private final String description;

    BaseField(String fieldName, String description) {
        this.fieldName = fieldName;
        this.description = description;
    }

}
