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

    private long startTime;

    private long delayTime;


    @Override
    public long getDelay(@NotNull TimeUnit unit) {
        long diff = delayTime - (System.currentTimeMillis() - startTime);
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(@NotNull Delayed o) {
        if (this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS)) {
            return -1;
        }
        if (this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)) {
            return 1;
        }
        return 0;
    }
}
