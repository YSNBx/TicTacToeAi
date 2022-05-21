package tictactoe;

import java.util.Scanner;

import tictactoe.gameengine.InitGame;

public class Main {
    public static void main(String[] args) {
        InitGame game = new InitGame(new Scanner(System.in));
        game.init();
    }
}