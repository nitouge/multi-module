package com.lsj.ssm.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AnnoBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        log.warn(">>> BeanPostProcessor前置处理: {}, class: {}", beanName, bean.getClass());
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        log.warn(">>> BeanPostProcessor后置处理: {}, class: {}", beanName, bean.getClass());
        return bean;
    }
}
