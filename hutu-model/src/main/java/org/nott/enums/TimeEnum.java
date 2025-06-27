package org.nott.enums;

import lombok.Getter;

@Getter
public enum TimeEnum {

    SECOND(1000L, 1L, "秒"),
    MINUTE(60 * 1000L, 60L, "分钟"),
    HOUR(60 * 60 * 1000L, 60 * 60L, "小时"),
    DAY(24 * 60 * 60 * 1000L, 24 * 60 * 60L, "天"),
    WEEK(7 * 24 * 60 * 60 * 1000L, 7 * 24 * 60 * 60L, "周"),
    MONTH(30 * 24 * 60 * 60 * 1000L, 30 * 24 * 60 * 60L, "月"),
    YEAR(365 * 24 * 60 * 60 * 1000L, 365 * 24 * 60 * 60L, "年");

    private final Long millis;
    private final Long seconds;
    private final String unit;

    TimeEnum(Long millis, Long seconds, String unit) {
        this.millis = millis;
        this.seconds = seconds;
        this.unit = unit;
    }

}
