package org.nott.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

/**
 * @author Nott
 * @date 2024-12-24
 */
@Component
public class AsyncExecutorConfig implements AsyncConfigurer {

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        //核心线程数：线程池创建时候初始化的线程数，方法: 返回可用处理器的Java虚拟机的数量。
        executor.setCorePoolSize(Runtime.getRuntime().availableProcessors());

        //线程池最大的线程数，默认是：Integer.MAX_VALUE
        //只有在缓冲队列满了之后才会申请超过核心线程数的线程
        executor.setMaxPoolSize(Runtime.getRuntime().availableProcessors()*5);

        //线程池的队列容量，用来缓冲执行任务的队列
        //默认是：Integer.MAX_VALUE
        executor.setQueueCapacity(Runtime.getRuntime().availableProcessors()*2);

        //线程池名的前缀：设置好了之后可以方便我们定位处理任务所在的线程池
        executor.setThreadNamePrefix("Hutu-async-executor-");

        //允许线程的空闲时间N秒：当超过了核心线程出之外的线程在空闲时间到达之后会被销毁
        //默认是60秒
        //executor.setKeepAliveSeconds(120);

        //线程池对拒绝任务的处理策略：这里采用了CallerRunsPolicy策略
        //当线程池没有处理能力的时候，该策略会直接在 execute 方法调用的线程中运行被拒绝的任务；
        //如果执行程序已关闭，则会丢弃该任务
        //executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        return executor;
    }
}
