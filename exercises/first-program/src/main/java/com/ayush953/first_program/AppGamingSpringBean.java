package com.ayush953.first_program;

import com.ayush953.first_program.game.GameRunner;
import com.ayush953.first_program.game.GamingConsole;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.ayush953.first_program.game")
public class AppGamingSpringBean {
    public static void main(String[] args) {
        try(AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppGamingSpringBean.class)){
            context.getBean(GameRunner.class).run();
        }

    }
}
