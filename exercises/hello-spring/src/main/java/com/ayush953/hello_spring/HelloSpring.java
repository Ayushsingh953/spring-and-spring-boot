package com.ayush953.hello_spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HelloSpring {
    public static void main(String[] args) {
        // Configure things that we want spring to manage : @Configuration class
        // Configured in HelloSpringConfiguration class
        // Launch Spring context
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HelloSpringConfiguration.class);
    }
}
