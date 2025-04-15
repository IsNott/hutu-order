package org.nott.security.scanner;

import lombok.extern.slf4j.Slf4j;
import org.nott.common.annotation.JustLogin;
import org.nott.common.annotation.PayApi;
import org.nott.common.utils.SpringContextUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author Nott
 * @date 2024-6-17
 */

@Slf4j
@Component
public class PermissionAnnotationScanner implements ApplicationListener<ContextRefreshedEvent> {

    private static final ConcurrentHashMap<String, Object> MAP = new ConcurrentHashMap<>(16);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if(SpringContextUtil.getApplicationContext() == null){
            log.debug("ApplicationContext is null, Do PermissionAnnotationScan Failed...");
            return;
        }
        ApplicationContext applicationContext = SpringContextUtil.getApplicationContext();
        String[] controllerBeanNames = applicationContext.getBeanNamesForAnnotation(RestController.class);
        for (String beanName : controllerBeanNames) {
            Object bean = applicationContext.getBean(beanName);
            Method[] methods = bean.getClass().getMethods();
            List<Method> methodList = Arrays.asList(methods).stream().filter(r -> r.isAnnotationPresent(JustLogin.class))
                    .collect(Collectors.toList());
            for (Method method : methodList) {
                log.info("Add Method {}",method.getName());
                MAP.put(method.getName(),method);
            }
        }
    }
}
