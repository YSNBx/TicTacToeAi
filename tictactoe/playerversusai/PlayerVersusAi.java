package tictactoe.playerversusai;

import tictactoe.components.GameGrid;
import tictactoe.components.Ai;
import tictactoe.playerconstants.PlayerTurnEnums;

import java.util.Scanner;


public abstract class PlayerVersusAi {
    private GameGrid gameGrid;
    private char playerTurn;
    private Ai artificialPlayer;

    public PlayerVersusAi(GameGrid gameGrid, Ai artificialPlayer) {
        this.gameGrid = gameGrid;
        this.playerTurn = PlayerTurnEnums.PLAYER_X.getTurn();
        this.artificialPlayer = artificialPlayer;
    }
}
