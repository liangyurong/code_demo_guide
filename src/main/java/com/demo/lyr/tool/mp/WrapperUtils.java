package com.demo.lyr.tool.mp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @description: 条件查询为空判断
 **/
public class WrapperUtils {
    /**
     * 等于操作，如果为空则不生成条件
     *
     * @param lambda
     * @param fn
     * @param value
     * @param <T>
     */
    public static <T> void eq(LambdaQueryWrapper lambda, SFunction<T, ?> fn, Object value) {
        if (value != null) {
            if (value instanceof String && StringUtils.isBlank((String) value)) {
                return;
            }
            lambda.eq(fn, value);
        }
    }

    /**
     * like操作，如果为空则不生成条件
     *
     * @param lambda
     * @param fn
     * @param value
     * @param <T>
     */
    public static <T> void like(LambdaQueryWrapper lambda, SFunction<T, ?> fn, Object value) {
        if (value != null) {
            if (value instanceof String && StringUtils.isBlank((String) value)) {
                return;
            }
            lambda.like(fn, value);
        }
    }

    public static <T> void between(LambdaQueryWrapper lambda, SFunction<T, ?> fn, Object value1, Object value2) {
        if (value1 != null && value2 != null) {
            if (value1 instanceof String && StringUtils.isBlank((String) value1) &&
                    value2 instanceof String && StringUtils.isBlank((String) value2)) {
                return;
            }
            lambda.between(fn, value1, value2);
        }
    }
    /**
     * set操作，如果为空则不生成条件
     *
     * @param lambda
     * @param fn
     * @param value
     * @param <T>
     */
    public static <T> void set(LambdaUpdateWrapper lambda, SFunction<T, ?> fn, Object value) {
        if (value != null) {
            if (value instanceof String && StringUtils.isBlank((String) value)) {
                return;
            }
            lambda.set(fn, value);
        }
    }

    /**
     * ge操作，如果为空则不生成条件
     *
     * @param lambda
     * @param fn
     * @param value
     * @param <T>
     */
    public static <T> void ge(LambdaUpdateWrapper lambda, SFunction<T, ?> fn, Object value) {
        if (value != null) {
            if (value instanceof String && StringUtils.isBlank((String) value)) {
                return;
            }
            lambda.ge(fn, value);
        }
    }

    /**
     * in操作，如果为空则不生成条件
     *
     * @param lambda
     * @param fn
     * @param values
     * @param <T>
     */
    @Deprecated
    public static <T> void in(LambdaQueryWrapper lambda, SFunction<T, ?> fn, Object... values) {
        if (values != null && values.length > 0) {
            lambda.in(fn, values);
        }
    }

    /**
     * notIn操作，如果为空则不生成条件
     *
     * @param lambda
     * @param fn
     * @param value
     * @param <T>
     */
    public static <T> void notIn(LambdaQueryWrapper lambda, SFunction<T, ?> fn, Object value) {
        if (value != null) {
            if (value instanceof String && StringUtils.isBlank((String) value)) {
                return;
            }
            lambda.notIn(fn, value);
        }
    }

    /**
     * 不等于操作，如果为空则不生成条件
     *
     * @param lambda
     * @param fn
     * @param value
     * @param <T>
     */
    public static <T> void ne(LambdaQueryWrapper lambda, SFunction<T, ?> fn, Object value) {
        if (value != null) {
            if (value instanceof String && StringUtils.isBlank((String) value)) {
                return;
            }
            lambda.ne(fn, value);
        }
    }


    /**
     * 根据某个字段去重数据
     * @param
     * @return
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

}


