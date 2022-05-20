package tictactoe;

import java.util.Scanner;

import tictactoe.gameengine.ChooseGame;

public class Main {
    public static void main(String[] args) {
        ChooseGame game = new ChooseGame(new Scanner(System.in));
        game.executeProgram();
    }
}