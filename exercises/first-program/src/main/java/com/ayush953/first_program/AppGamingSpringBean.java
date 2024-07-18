package com.ayush953.first_program;

import com.ayush953.first_program.game.GameRunner;
import com.ayush953.first_program.game.GamingConsole;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppGamingSpringBean {
    public static void main(String[] args) {
        try(AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(GamingConfiguration.class)){
            context.getBean(GameRunner.class).run();
        }

    }
}
