package com.ayush953.hello_spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Introduced in JDK 16
// Automatically creates constructor,getter and setter
record Person(String name,int age){}
@Configuration
public class HelloSpringConfiguration {
    @Bean
    public String name(){
        return "XYZ";
    }

    @Bean
    public int age(){
        return 10;
    }

    @Bean
    public Person person(){
        return new Person("Jack", 18);
    }
}
