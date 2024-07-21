package com.ayush953.xml_configuration_example;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class XmlConfiguration {
    public static void main(String[] args) {

        // Launching context which uses XML configuration
        try(ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("contextConfiguration.xml")){
            System.out.println("Printing all beans :");
            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);

            System.out.println("Printing values of beans");
            System.out.println(context.getBean("name"));
            System.out.println(context.getBean("age"));
        }
    }
}
