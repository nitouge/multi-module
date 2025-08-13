package com.lsj.ssm.processor;

import com.lsj.ssm.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@DependsOn("beanConfig")
@Component
@Slf4j
public class AnnoBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
        //     log.info("beanDefinitionName:{}", beanDefinitionName);
        // }

        // 获取 BeanDefinition
        BeanDefinition addressBD = beanFactory.getBeanDefinition("address");
        if (addressBD instanceof AbstractBeanDefinition) {
            AbstractBeanDefinition abd = (AbstractBeanDefinition) addressBD;
            log.warn("Address bean has class: {}", abd.hasBeanClass());
            log.warn("Address factoryBeanName: {}", abd.getFactoryBeanName());
            log.warn("Address factoryMethodName: {}", abd.getFactoryMethodName());
        }
        addressBD.getPropertyValues().add("name", "居住地址");

        BeanDefinition personBD = beanFactory.getBeanDefinition("person");
        log.warn("Person >>> BeanFactoryPostProcessor#postProcess BeanDefinition class: {}", personBD.getBeanClassName());
        personBD.getPropertyValues().add("username", "JAVA BOY");
    }
}
