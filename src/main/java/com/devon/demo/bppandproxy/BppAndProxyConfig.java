package com.devon.demo.bppandproxy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BppAndProxyConfig {

    @Bean
    public Cat cat(){
        return new Cat();
    }

    @Bean
    public Dog dog(){
        return new Dog();
    }

    @Bean
    public MethodTImedPoxy methodTImedPoxy(){
        return new MethodTImedPoxy();
    }
}
