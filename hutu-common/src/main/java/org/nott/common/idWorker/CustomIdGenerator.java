package org.nott.common.idWorker;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;

import lombok.extern.slf4j.Slf4j;

/**
 * 自定义ID生成器
 */
@Slf4j
public class CustomIdGenerator implements IdentifierGenerator {

    /**
     * workerId，机器id
     * datacenterId，数据标识id
     */
    private final SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);

    /**
     * AtomicLong是作用是对长整形进行原子操作。
     * 在32位操作系统中，64位的long 和 double 变量由于会被JVM当作两个分离的32位来进行操作，所以不具有原子性。
     * 而使用AtomicLong能让long的操作保持原子型。
     * @param entity
     * @return
     */
    @Override
    public Long nextId(Object entity) {
        AtomicLong al = new AtomicLong(idWorker.nextId());
        final long id = al.get();
        return id;
    }
}
