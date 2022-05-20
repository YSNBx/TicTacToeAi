package tictactoe;

import tictactoe.gameengine.GameBoard;
import tictactoe.components.Ai;
import tictactoe.components.GameGrid;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard(new GameGrid(), new Ai());
        gameBoard.start();
    }
}