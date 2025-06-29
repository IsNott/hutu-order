package org.nott.security.configuration;

import org.nott.security.interceptor.AuthorizationInterceptor;
import org.nott.security.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Nott
 * @date 2024-6-6
 */
@Configuration
public class InterceptorConfiguration {

    @Value("${interceptor.authorize}")
    private boolean needAuthorize;

    @Value("${interceptor.dev-mode}")
    private boolean devMode;

    @Bean
    WebMvcConfigurer createWebMvcConfigurer() {
        return new WebMvcConfigurer() {
            public void addInterceptors(InterceptorRegistry registry) {
                if(!devMode) registry.addInterceptor(new LoginInterceptor());

                if(needAuthorize && !devMode){
                    registry.addInterceptor(new AuthorizationInterceptor());
                }
            }

            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
                registry.addResourceHandler("doc.html")
                        .addResourceLocations("classpath:/META-INF/resources/");
                registry.addResourceHandler("/swagger-ui.html")
                        .addResourceLocations("classpath:/META-INF/resources/");
                registry.addResourceHandler("/webjars/**")
                        .addResourceLocations("classpath:/META-INF/resources/webjars/");
            }
        };
    }
}
