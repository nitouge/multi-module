package com.lsj.ssm.processor;

import com.lsj.ssm.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // 判断是否为根容器（防止执行两次）
        if (event.getApplicationContext().getParent() == null) {
            log.info("########### Spring容器初始化完成，开始执行初始化逻辑 ########### ");
            // 调用 Bean 方法、加载缓存、注册服务等
            ApplicationContext applicationContext = event.getApplicationContext();
            Person person = applicationContext.getBean(Person.class);
            person.say();
        }
    }
}
