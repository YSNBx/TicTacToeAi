package tictactoe.playervsaibase;

import java.util.Scanner;

import tictactoe.components.GameBoard;
import tictactoe.playerconstants.PlayerTurnEnums;
import tictactoe.ui.SuperInterface;

public abstract class PlayerVsAiAbstract implements SuperInterface {
    protected final GameBoard gameGrid;
    protected char playerTurn;
    protected final Scanner scanner;

    public PlayerVsAiAbstract(GameBoard gameGrid) {
        this.gameGrid = gameGrid;
        this.playerTurn = PlayerTurnEnums.PLAYER_X.getTurn();
        this.scanner = new Scanner(System.in);
        this.gameGrid.initGrid();
    }

    @Override
    public abstract void start();
    public abstract void checkTurnAndMakeMove();
}
