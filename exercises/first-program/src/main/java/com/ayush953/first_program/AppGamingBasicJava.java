package com.ayush953.first_program;

import com.ayush953.first_program.game.*;

public class AppGamingBasicJava {
    public static void main(String[] args) {
//        Mario game = new Mario();
        SuperContra game = new SuperContra();
        GameRunner gameRunner = new GameRunner(game);
        gameRunner.run();
    }
}
