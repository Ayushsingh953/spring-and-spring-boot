package com.ayush953.first_program.game;

public class GameRunner {
    Mario game;
    public GameRunner(Mario game){
        this.game = game;
    }
    public void run(){
        System.out.println("Running game..."+game);
        game.up();
        game.down();
        game.left();
        game.right();
    }
}
