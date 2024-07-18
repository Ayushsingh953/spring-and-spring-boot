package com.ayush953.hello_spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloSpringConfiguration {
    @Bean
    public String name(){
        return "Ayush";
    }
}
