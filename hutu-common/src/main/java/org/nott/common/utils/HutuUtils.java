package org.nott.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;
import org.nott.common.exception.HutuBizException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.springframework.beans.BeanUtils.getPropertyDescriptor;
import static org.springframework.beans.BeanUtils.getPropertyDescriptors;

/**
 * @author Nott
 * @date 2024-6-17
 */

public class HutuUtils {

    public static class FORMAT{
        public static final SimpleDateFormat MD = new SimpleDateFormat("MMdd");
        public static final SimpleDateFormat YEAR = new SimpleDateFormat("yyyy");
        public static final SimpleDateFormat DATETIME = new SimpleDateFormat("yyyyMMddHHmmss");
    }

    public static void requireTrue(boolean condition) throws HutuBizException{
        requireTrue(condition,null);
    }

    public static void requireFalse(boolean condition) throws HutuBizException{
        requireTrue(!condition,null);
    }

    public static void requireTrue(boolean condition, @Nullable String msg) throws HutuBizException{
        if(!condition){
            throw new HutuBizException(isNotEmpty(msg) ? msg : "业务条件不符合要求");
        }
    }

    public static void requireFalse(boolean condition,@Nullable String msg) throws HutuBizException{
        requireTrue(!condition,msg);
    }

    public static void requireNotNull(Object o) throws HutuBizException {
        requireNotNull(o, null);
    }

    public static void requireNotNull(Object o,String message) throws HutuBizException {
        if(o == null){
            throw new HutuBizException(StringUtils.isNotEmpty(message) ? message : "获取信息为空，请检查");
        }
    }

    public static void requireNotNull(boolean condition,Object o,String message) throws HutuBizException {
        if(condition){
            requireNotNull(o,message);
        }
    }

    public static void requireNull(Object o) throws HutuBizException{
        requireNull(o, null);
    }

    public static void requireNull(Object o,String message) throws HutuBizException {
        if(o == null){
            throw new HutuBizException(StringUtils.isNotEmpty(message) ? message : "获取信息不为空，请检查");
        }
    }

    public static void copyProperties(Object source,Object target){
        copyProperties(source,target,true);
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

    public static <T, S> S transToVo(T source, Class<S> targetClass) {
        S s;
        try {
            s = targetClass.newInstance();
            copyProperties(source, s);
        } catch (Exception e) {
            throw new HutuBizException("Trans obj to vo failed");
        }
        return s;
    }

    public static boolean isEmpty(Object o){
        if(o instanceof String){
            return StringUtils.isEmpty((String) o);
        } else if (o instanceof Collection) {
            return CollectionUtils.isEmpty((Collection<?>) o);
        } else if (o instanceof Map){
            return CollectionUtils.isEmpty((Map<?, ?>) o);
        } else {
            return o == null;
        }
    }

    public static boolean isNotEmpty(Object o){
        return !isEmpty(o);
    }

    public static <T> T getIfValue(T value,T ifValue){
        return isEmpty(value) ? ifValue : value;
    }

    public static int pageOffset(Integer page,Integer size){
        return ((page - 1) * size);
    }

    public static <T> void collectAddNotExcept(Collection<T> collection, T element) {
        if (collection == null || element == null) {
            return;
        }
        collection.add(element);
    }

    public static void copyProperties(Object source,Object target,boolean skipNullProp){
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");
        Class<?> actualEditable = target.getClass();
        PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
        PropertyDescriptor[] var7 = targetPds;
        int var8 = targetPds.length;

        for(int var9 = 0; var9 < var8; ++var9) {
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

                            if(skipNullProp){
                                if(isNotEmpty(value)){
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

    public static boolean checkWeekDayAndTimeForNow(String startTimeToCheck, String endTimeToCheck, Integer startDayOfWeek, Integer endDayOfWeek) {
        LocalTime start = LocalTime.parse(startTimeToCheck, DateTimeFormatter.ofPattern("HH:mm:ss"));
        LocalTime end = LocalTime.parse(endTimeToCheck, DateTimeFormatter.ofPattern("HH:mm:ss"));
        return checkWeekDayAndTimeForNow(start,end,DayOfWeek.of(startDayOfWeek),DayOfWeek.of(endDayOfWeek));
    }

    public static boolean checkWeekDayAndTimeForNow(LocalTime startTimeToCheck, LocalTime endTimeToCheck, DayOfWeek startDayOfWeek, DayOfWeek endDayOfWeek) {
        LocalDate today = LocalDate.now();

        DayOfWeek dayOfWeek = today.getDayOfWeek();

        LocalTime now = LocalTime.now();

        return startDayOfWeek.ordinal() <= dayOfWeek.ordinal() && endDayOfWeek.ordinal() >= dayOfWeek.ordinal() &&
                startTimeToCheck.isBefore(now) && endTimeToCheck.isAfter(now);
    }

}
