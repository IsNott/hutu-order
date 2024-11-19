package org.nott.common.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.nott.common.config.JedisPoolYmlConfig;
import org.nott.common.config.RedisConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

/**
 * redis配置类
 * @author Nott
 * @date 2024-6-6
 */
@ConditionalOnProperty(prefix = "spring.redis",name = "host")
@Configuration
@RequiredArgsConstructor
public class RedisConfiguration{

    private final RedisConfig redisConfig;

    private final JedisPoolYmlConfig jedisPoolYmlConfig;


    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(jedisPoolYmlConfig.getMaxActive());
        config.setMinIdle(jedisPoolYmlConfig.getMinIdle());
        config.setMaxIdle(jedisPoolYmlConfig.getMaxIdle());
        config.setBlockWhenExhausted(true);
        config.setTestWhileIdle(true);
        config.setTestOnBorrow(true);
        config.setTimeBetweenEvictionRunsMillis(3000);
        return config;
    }

    @Bean
    @DependsOn("jedisPoolConfig")
    public RedisConnectionFactory getRedisConnectionFactory(@Qualifier("jedisPoolConfig") JedisPoolConfig jedisPoolConfig){
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(redisConfig.getHost());
        configuration.setPort(redisConfig.getPort());
        configuration.setDatabase(redisConfig.getDatabase());
        if(StringUtils.isNotEmpty(redisConfig.getPassword())){
            configuration.setPassword(redisConfig.getPassword());
        }
        JedisClientConfiguration.JedisClientConfigurationBuilder jpcf =  JedisClientConfiguration.builder();
        jpcf.connectTimeout(Duration.ofMillis(jedisPoolYmlConfig.getTimeOut()))
                .readTimeout(Duration.ofMillis(jedisPoolYmlConfig.getTimeOut()))
                .usePooling().poolConfig(jedisPoolConfig);
        JedisClientConfiguration jedisClientConfiguration = jpcf.build();
        return new JedisConnectionFactory(configuration, jedisClientConfiguration);
    }

    @Bean("redisTemplate")
    public RedisTemplate<String, Object> redisTemplate() {
        //1.创建redisTemplate模板
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        //2.关联redisConnectionFactory
        template.setConnectionFactory(getRedisConnectionFactory(jedisPoolConfig()));
        //3.创建序列化类
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        //4.设置可见度
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        //5.启动默认的类型
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        //6.序列化类，对象映射设置
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
//        7.设置value的转化格式和key的转换格式
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setKeySerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }





}
