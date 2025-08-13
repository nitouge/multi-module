package com.lsj.ssm.entity;

import com.lsj.ssm.config.Address;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Person implements BeanNameAware, BeanFactoryAware, InitializingBean, DisposableBean {

    private String username;

    private Address address;

    public Person() {
        System.out.println("1.Person构造方法");
    }

    public void setUsername(String username) {
        System.out.println("2.Person#setUsername方法被调用");
        this.username = username;
    }

    @Override
    public void setBeanName(String beanName) {
        System.out.println("3.Person#BeanNameAware#setBeanName: " + beanName);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        System.out.println("4.Person#BeanFactoryAware#setBeanFactory");
    }

    @PostConstruct
    public void call() {
        System.out.println("5.Person#@PostConstruct#call被调用");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("6.Person#InitializingBean#afterPropertiesSet");
    }

    public void customInit() {
        System.out.println("7.Person#自定义init-method被调用");
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("8.Person#@PreDestroy#cleanup被调用");
    }

    @Override
    public void destroy() {
        System.out.println("9.Person#DisposableBean#destroy#destroy");
    }

    public void customDestroy() {
        System.out.println("10.Person#自定义destroy-method被调用");
    }


    public String getUsername() {
        return username;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void say() {
        System.out.println("Person say......");
        // System.out.println("Hello, I'm " + name + ", I live in " + address.getCity() + ", " + address.getStreet());
        System.out.println("Hello, I'm " + username + ", I live in ");
        System.out.println(address.getCity());
        System.out.println(address.getDistrict());
        System.out.println("Person finish");
    }

    @Override
    public String toString() {
        return "Person{username='" + username + '\''
                + ", address=" + address +
                '}';
    }
}
