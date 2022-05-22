package tictactoe.playerversusai;

import tictactoe.components.GameGrid;
import tictactoe.components.Ai;
import tictactoe.playerconstants.PlayerTurnEnums;

public abstract class InterfacePlayerVersusAi {
    private GameGrid gameGrid;
    private char playerTurn;
    private Ai artificialPlayer;

    public InterfacePlayerVersusAi(GameGrid gameGrid, Ai artificialPlayer) {
        this.gameGrid = gameGrid;
        this.playerTurn = PlayerTurnEnums.PLAYER_X.getTurn();
        this.artificialPlayer = artificialPlayer;
    }
}
