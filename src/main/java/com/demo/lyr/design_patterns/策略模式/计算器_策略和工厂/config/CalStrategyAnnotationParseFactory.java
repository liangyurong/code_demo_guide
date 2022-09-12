package com.demo.lyr.design_patterns.策略模式.计算器_策略和工厂.config;

import com.demo.lyr.design_patterns.策略模式.计算器_策略和工厂.策略接口.CalStrategy;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 通过实现 ApplicationContextAware 接口可以获取所有的spring bean，是因为spring会自动执行setApplicationAware .
 */
@Component
public class CalStrategyAnnotationParseFactory implements ApplicationContextAware {

    private  ApplicationContext applicationContext;

    private Map<String, CalStrategy> calMap = new HashMap<>(8);

    // 初始化calMap
    // 获取@CalMethod注解，放入calMap。key是注解的value值，value是CalStrategy对象
    private Map<String, CalStrategy> getCalMap(){
        if(CollectionUtils.isEmpty(calMap)){
            Map<String, Object> beansMap = applicationContext.getBeansWithAnnotation(CalMethod.class);
            for(Object bean : beansMap.values()){
                String annotationValue = bean.getClass().getAnnotation(CalMethod.class).value();
                calMap.put(annotationValue,(CalStrategy) bean);
            }
        }
        return calMap;
    }

    public CalStrategy getStrategy(String inputVal){
        Map<String, CalStrategy> map = getCalMap();
        CalStrategy strategy = map.get(inputVal);

        if(strategy == null){
            throw new UnsupportedOperationException("输入的计算方法不支持："+inputVal);
        }

        return strategy;
    }

    /**
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境
     *
     * @param applicationContext spring上下文对象
     * @throws BeansException 抛出spring异常
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
