package org.nott.common.aop;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author tasteTheWorld
 * @date 12月
 * version 1.0.0
 */

@Configuration
@EnableTransactionManagement
public class TransactionAspect {

    @Autowired
    private TransactionManager transactionManager;

    // 配置事务拦截
    @Bean
    public TransactionInterceptor txAdvice() {
        // 只读
        RuleBasedTransactionAttribute readOnlyTx = new RuleBasedTransactionAttribute();
        readOnlyTx.setReadOnly(true);
        readOnlyTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        // 读写
        RuleBasedTransactionAttribute requiredTx = new RuleBasedTransactionAttribute();
        requiredTx.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        requiredTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        requiredTx.setTimeout(10); // 超时时间

        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();

        Map<String, TransactionAttribute> txMap = new HashMap<>();

        // 只读
        txMap.put("get*", readOnlyTx);
        txMap.put("find*", readOnlyTx);
        txMap.put("query*", readOnlyTx);
        txMap.put("select*", readOnlyTx);
        txMap.put("list*", readOnlyTx);
        txMap.put("details", readOnlyTx);
        txMap.put("count*", readOnlyTx);

        // 写方法
        txMap.put("save*", requiredTx);
        txMap.put("add*", requiredTx);
        txMap.put("create*", requiredTx);
        txMap.put("insert*", requiredTx);
        txMap.put("update*", requiredTx);
        txMap.put("modify*", requiredTx);
        txMap.put("edit*", requiredTx);
        txMap.put("delete*", requiredTx);
        txMap.put("remove*", requiredTx);
        txMap.put("batch*", requiredTx);
        txMap.put("process*", requiredTx);
        txMap.put("execute*", requiredTx);
        txMap.put("handle*", requiredTx);

        // 特殊方法
        txMap.put("*", requiredTx);

        source.setNameMap(txMap);

        return new TransactionInterceptor(transactionManager, source);
    }

    /**
     * 配置事务切面，使用通配符匹配包
     */
    @Bean
    public Advisor txAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        String expression =
                "(execution(* org.nott..service..*.*(..)))";

        pointcut.setExpression(expression);

        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }
}
