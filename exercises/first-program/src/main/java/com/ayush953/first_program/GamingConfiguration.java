package com.ayush953.first_program;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ayush953.first_program.game.*;

@Configuration
public class GamingConfiguration {
    @Bean
    public GamingConsole game(){
        return new SuperContra();
    }

    @Bean
    public GameRunner gameRunner(GamingConsole game){
        return new GameRunner(game);
    }
}
