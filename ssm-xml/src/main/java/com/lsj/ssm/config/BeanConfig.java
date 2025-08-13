package com.lsj.ssm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = true)
public class BeanConfig {

    @Bean(name = "address")
    public Address getAddress(City city) {
        return new Address(city.getName(), "江宁区");
    }

    @Bean(name = "City")
    public City getCity() {
        return new City("南京市", "210000");
    }
}
