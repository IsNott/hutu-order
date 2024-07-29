package org.nott.common.configuration;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusPropertiesCustomizer;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.extension.incrementer.H2KeyGenerator;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.IllegalSQLInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.nott.common.idWorker.CustomIdGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nott
 * @date 2024-5-24
 */

@Configuration
public class MbpConfiguration {

    @Configuration
    public class MybatisPlusConfig {

        /**
         * 添加分页插件
         */
        @Bean
        public MybatisPlusInterceptor mybatisPlusInterceptor() {
            MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
            interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL)); // 如果配置多个插件, 切记分页最后添加
            // 如果有多数据源可以不配具体类型, 否则都建议配上具体的 DbType
            return interceptor;
        }

        /**
         * 添加非法SQL拦截器
         */
//        @Bean
//        public MybatisPlusInterceptor mybatisPlusSQLInterceptor() {
//            MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
//            //
//            interceptor.addInnerInterceptor(new IllegalSQLInnerInterceptor());
//            return interceptor;
//        }

        /**
         * 添加自定义id生成器
         */
        @Bean
        public IdentifierGenerator identifierGenerator() {
            return new CustomIdGenerator();
        }

    }
}
