package org.nott.enums;

import lombok.Getter;

@Getter
public enum HandleOrderExpireType {
    DELAYED_QUEUE("delayedQueue");

    HandleOrderExpireType(String name) {
        this.name = name;
    }

    private String name;
}
