package com.ayush953.hello_spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Introduced in JDK 16
// Automatically creates constructor,getter and setter
record Person(String name,int age,Address address){}

// Address record
record Address(String street,String city){ }
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
        return new Person("Sherlock holmes", 25,new Address("baker street","London"));
    }

    @Bean
    // create bean using existing bean by method call
    public Person person2(){
        return new Person(name(),age(),address());
    }

    @Bean
    // create bean using existing bean using parameter
    public Person person3(String name,int age,Address addr2){
        return new Person(name,age,addr2);
    }

    @Bean(name="addr")
    public Address address(){
        return new Address("Baker street", "London");
    }
    @Bean(name="addr2")
    public Address address2(){
        return new Address("Main street", "London");
    }
}
