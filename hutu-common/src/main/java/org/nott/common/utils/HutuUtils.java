package org.nott.common.utils;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;
import org.nott.common.exception.HutuBizException;
import org.springframework.beans.FatalBeanException;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.beans.BeanUtils.getPropertyDescriptor;
import static org.springframework.beans.BeanUtils.getPropertyDescriptors;

/**
 * @author Nott
 * @date 2024-6-17
 */

public class HutuUtils {

    private static final DefaultParameterNameDiscoverer defaultParameterNameDiscoverer = new DefaultParameterNameDiscoverer();
    // SpEL格式化器
    private static final SpelExpressionParser parser = new SpelExpressionParser();


    public static class FORMAT {
        public static final SimpleDateFormat MD = new SimpleDateFormat("MMdd");
        public static final SimpleDateFormat YEAR = new SimpleDateFormat("yyyy");
        public static final SimpleDateFormat DATETIME = new SimpleDateFormat("yyyyMMddHHmmss");
        public static final SimpleDateFormat DATE = new SimpleDateFormat("yyyy-MM-dd");
    }

    public static void requireTrue(boolean condition) throws HutuBizException {
        requireTrue(condition, null);
    }

    public static void requireFalse(boolean condition) throws HutuBizException {
        requireTrue(!condition, null);
    }

    public static void requireTrue(boolean condition, @Nullable String msg) throws HutuBizException {
        if (!condition) {
            throw new HutuBizException(isNotEmpty(msg) ? msg : "业务条件不符合要求");
        }
    }

    public static void requireFalse(boolean condition, @Nullable String msg) throws HutuBizException {
        requireTrue(!condition, msg);
    }

    public static void requireNotNull(Object o) throws HutuBizException {
        requireNotNull(o, null);
    }

    public static void requireNotNull(Object o, String message) throws HutuBizException {
        if (isEmpty(o)) {
            throw new HutuBizException(StringUtils.isNotEmpty(message) ? message : "获取信息为空，请检查");
        }
    }

    public static void requireAndNotNull(String message, Object... o) throws HutuBizException {
        List<Object> list = Arrays.stream(o).filter(Objects::isNull).collect(Collectors.toList());
        if(list.size() == o.length){
            throw new HutuBizException(StringUtils.isNotEmpty(message) ? message : "获取信息为空，请检查");
        }
    }

    public static void requireNotNull(boolean condition, Object o, String message) throws HutuBizException {
        if (condition) {
            requireNotNull(o, message);
        }
    }

    public static void requireNull(Object o) throws HutuBizException {
        requireNull(o, null);
    }

    public static void requireNull(Object o, String message) throws HutuBizException {
        if (isNotEmpty(o)) {
            throw new HutuBizException(StringUtils.isNotEmpty(message) ? message : "获取信息不为空，请检查");
        }
    }

    public static void copyProperties(Object source, Object target) {
        copyProperties(source, target, true);
    }

    public static <T, S> List<S> transToVos(List<T> objs, Class<S> sClazz) {
        List<S> sList = new ArrayList<>();
        try {
            for (T obj : objs) {
                S s = sClazz.newInstance();
                copyProperties(obj, s);
                sList.add(s);
            }
        } catch (Exception e) {
            throw new HutuBizException("Trans obj to vo failed");
        }
        return sList;
    }

    public static <T, S> S transToObject(T source, Class<S> targetClass) {
        S s;
        try {
            s = targetClass.newInstance();
            copyProperties(source, s);
        } catch (Exception e) {
            throw new HutuBizException("Trans obj to vo failed");
        }
        return s;
    }

    public static boolean isEmpty(Object o) {
        if (o instanceof String) {
            return StringUtils.isEmpty((String) o);
        } else if (o instanceof Collection) {
            return CollectionUtils.isEmpty((Collection<?>) o);
        } else if (o instanceof Map) {
            return CollectionUtils.isEmpty((Map<?, ?>) o);
        } else {
            return o == null;
        }
    }

    public static boolean isNotEmpty(Object o) {
        return !isEmpty(o);
    }

    public static <T> T getIfValue(T value, T ifValue) {
        return isEmpty(value) ? ifValue : value;
    }

    public static int pageOffset(Integer page, Integer size) {
        return ((page - 1) * size);
    }

    public static <T> void collectAddNotExcept(Collection<T> collection, T element) {
        if (collection == null || element == null) {
            return;
        }
        collection.add(element);
    }

    public static void copyProperties(Object source, Object target, boolean skipNullProp) {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");
        Class<?> actualEditable = target.getClass();
        PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
        PropertyDescriptor[] var7 = targetPds;
        int var8 = targetPds.length;

        for (int var9 = 0; var9 < var8; ++var9) {
            PropertyDescriptor targetPd = var7[var9];
            Method writeMethod = targetPd.getWriteMethod();
            if (writeMethod != null) {
                PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
                if (sourcePd != null) {
                    Method readMethod = sourcePd.getReadMethod();
                    if (readMethod != null && ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
                        try {
                            if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                                readMethod.setAccessible(true);
                            }

                            Object value = readMethod.invoke(source);
                            if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                writeMethod.setAccessible(true);
                            }

                            if (skipNullProp) {
                                if (isNotEmpty(value)) {
                                    writeMethod.invoke(target, value);
                                }
                            }
                        } catch (Throwable var15) {
                            throw new FatalBeanException("Could not copy property '" + targetPd.getName() + "' from source to target", var15);
                        }
                    }
                }
            }
        }
    }

    /**
     * 检查当前时间是否在指定的星期和时间范围内
     *
     * @param startTimeToCheck 开始时间
     * @param endTimeToCheck   结束时间
     * @param startDayOfWeek   开始星期
     * @param endDayOfWeek     结束星期
     * @return 是否在范围内
     */
    public static boolean checkWeekDayAndTimeForNow(String startTimeToCheck, String endTimeToCheck, Integer startDayOfWeek, Integer endDayOfWeek) {
        LocalTime start = LocalTime.parse(startTimeToCheck, DateTimeFormatter.ofPattern("HH:mm:ss"));
        LocalTime end = LocalTime.parse(endTimeToCheck, DateTimeFormatter.ofPattern("HH:mm:ss"));
        return checkWeekDayAndTimeForNow(start, end, DayOfWeek.of(startDayOfWeek), DayOfWeek.of(endDayOfWeek));
    }

    /**
     * 检查当前时间是否在指定的星期和时间范围内
     *
     * @param startTimeToCheck 开始时间
     * @param endTimeToCheck   结束时间
     * @param startDayOfWeek   开始星期
     * @param endDayOfWeek     结束星期
     * @return 是否在范围内
     */
    public static boolean checkWeekDayAndTimeForNow(LocalTime startTimeToCheck, LocalTime endTimeToCheck, DayOfWeek startDayOfWeek, DayOfWeek endDayOfWeek) {
        LocalDate today = LocalDate.now();

        DayOfWeek dayOfWeek = today.getDayOfWeek();

        LocalTime now = LocalTime.now();

        return startDayOfWeek.ordinal() <= dayOfWeek.ordinal() && endDayOfWeek.ordinal() >= dayOfWeek.ordinal() &&
                startTimeToCheck.isBefore(now) && endTimeToCheck.isAfter(now);
    }

    /**
     * 通过SpEL表达式解析方法参数
     *
     * @param method      方法对象
     * @param args        方法参数数组
     * @param expression  SpEL表达式
     * @param tclass      返回值类型
     * @param <T>         返回值类型
     * @return 解析后的值
     */
    public static <T> T parseSpEl(Method method, Object[] args, String expression, Class<T> tclass) {
        if(StringUtils.isEmpty(expression)){
            return null;
        }
        // 以下步骤：获取注解中的SpEL表达式，并通过格式化获取参数值
        // 获取方法的形参名称，例：getUserName(String name)，则获取到name
        String[] parameterNames = defaultParameterNameDiscoverer.getParameterNames(method);
        // StandardEvaluationContext：SpEL上下文组件
        // 用作定义变量，将形参和实际参数设置为StandardEvaluationContext的variable（类似map{name:value}）
        StandardEvaluationContext ctx = new StandardEvaluationContext();
        for (int i = 0; i < parameterNames.length; i++) {
            ctx.setVariable(parameterNames[i], args[i]);
        }
        // 将注解中的SpEL表达式格式化并获取值
        return parser.parseExpression(expression).getValue(ctx,tclass);
    }

    /**
     * 将Page对象中的记录转换为指定类型的VO对象
     *
     * @param sourcePage 源Page对象
     * @param tClass     目标VO类
     * @param <E>        源记录类型
     * @param <T>        目标VO类型
     * @return 转换后的Page对象
     */
    public static <E,T> Page<T> transVOPage(Page<E> sourcePage, Class<T> tClass){
        List<E> records = sourcePage.getRecords();
        Page<T> tPage = new Page<>();
        if(isEmpty(records)){
            return tPage;
        }
        copyProperties(sourcePage,tPage);
        List<T> ts = transToVos(records, tClass);
        tPage.setRecords(ts);
        return tPage;
    }

    /**
     * 将对象数组转换为逗号分隔的字符串
     *
     * @param obj 对象数组
     * @return 逗号分隔的字符串
     */
    public static String joinByComma(Object... obj) {
        return join(",", obj);
    }

    public static String joinByColon(Object... obj) {
        return join(":", obj);
    }

    /**
     * 将对象数组转换为指定分隔符的字符串
     *
     * @param separator 分隔符
     * @param obj       对象数组
     * @return 指定分隔符的字符串
     */
    public static String join(String separator, Object... obj) {
        if (obj == null || obj.length == 0) {
            return "";
        }
        if(Arrays.stream(obj).allMatch(o -> o instanceof Collection)){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Object o : obj) {
            if (o != null) {
                sb.append(JSON.toJSONString(o)).append(separator);
            }
        }
        if (sb.length() > 0) {
            sb.delete(sb.length() - separator.length(), sb.length());
        }
        return sb.toString();
    }

}
