package tictactoe.playerversusai;

import java.util.Scanner;

import tictactoe.components.Ai;
import tictactoe.components.GameGrid;

public class PlayerVersusAiMed extends PlayerVersusAi {
    private GameGrid gameGrid;
    private Ai artificialPlayer;
    private char playerTurn;
    private final Scanner scanner;

    public PlayerVersusAiMed(GameGrid gameGrid, Ai artificialPlayer) {
        super(gameGrid, artificialPlayer);
        this.scanner = new Scanner(System.in);
    }

    
    
}
