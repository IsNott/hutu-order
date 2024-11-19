package org.nott.common.delayed;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author Nott
 * @date 2024-11-19
 */
@Data
public class DelayedTask<T> implements Delayed {

    private T data;

    private long availableTime;


    @Override
    public long getDelay(@NotNull TimeUnit unit) {
        return unit.convert(availableTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(@NotNull Delayed o) {
        Long i = this.getDelay(TimeUnit.SECONDS) - o.getDelay(TimeUnit.SECONDS);
        return i.intValue();
    }
}
