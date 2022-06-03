package tictactoe.aiversusai;

import tictactoe.ui.SuperInterface;
import tictactoe.components.Ai;
import tictactoe.components.GameBoard;
import tictactoe.playerconstants.PlayerTurnEnums;

public class AiVersusAiEasy implements SuperInterface {
    private GameBoard gameBoard;
    private char playerTurn;
    private Ai artificialPlayer;

    public AiVersusAiEasy(GameBoard gameBoard, Ai artificialPlayer) {
        this.gameBoard = gameBoard;
        gameBoard.initGrid();
        this.playerTurn = PlayerTurnEnums.PLAYER_X.getTurn();
        this.artificialPlayer = artificialPlayer;
    }

    @Override
    public void start() {
        while (true) {
            this.gameBoard.printGrid();
            
            if (this.isGameFinished()) {
                break;
            }

            if (this.playerTurn == PlayerTurnEnums.PLAYER_X.getTurn()) {
                this.turnForX();
                this.changePlayer(PlayerTurnEnums.PLAYER_O.getTurn());
            } else {
                this.turnForO();
                this.changePlayer(PlayerTurnEnums.PLAYER_X.getTurn());
            }
            this.gameBoard.increaseNumberOfFields();
        }
    }

    public void turnForO() {
        System.out.println("Making move level \"easy\"");

        this.makeMoveIfPossible();
    }

    public void turnForX() {
        System.out.println("Making move level \"easy\"");

        this.makeMoveIfPossible();
    }

    public void makeMoveIfPossible() {
        int[] coordinates = this.artificialPlayer.generateCoordinates();
        int firstCoordinate = coordinates[0];
        int secondCoordinate = coordinates[1];

        if (this.gameBoard.getBoard()[firstCoordinate][secondCoordinate] == '_') {
            this.gameBoard.setBoard(firstCoordinate, secondCoordinate, this.playerTurn);
        } else {
            this.makeMoveIfPossible();
        }
    }

    public void changePlayer(char turn) {
        this.playerTurn = turn;
    }

    //early version checkgamestate because i'm too lazy right now

    public boolean isGameFinished() {
        if (this.gameBoard.getBoard()[0][0] == 'X' && this.gameBoard.getBoard()[0][1] == 'X' && this.gameBoard.getBoard()[0][2] == 'X') {
            System.out.println("X wins\n");
            return true;
        }

        if (this.gameBoard.getBoard()[1][0] == 'X' && this.gameBoard.getBoard()[1][1] == 'X' && this.gameBoard.getBoard()[1][2] == 'X') {
            System.out.println("X wins\n");
            return true;
        }

        if (this.gameBoard.getBoard()[2][0] == 'X' && this.gameBoard.getBoard()[2][1] == 'X' && this.gameBoard.getBoard()[2][2] == 'X') {
            System.out.println("X wins\n");
            return true;
        }

        if (this.gameBoard.getBoard()[0][0] == 'O' && this.gameBoard.getBoard()[0][1] == 'O' && this.gameBoard.getBoard()[0][2] == 'O') {
            System.out.println("O wins\n");
            return true;
        }

        if (this.gameBoard.getBoard()[1][0] == 'O' && this.gameBoard.getBoard()[1][1] == 'O' && this.gameBoard.getBoard()[1][2] == 'O') {
            System.out.println("O wins\n");
            return true;
        }

        if (this.gameBoard.getBoard()[2][0] == 'O' && this.gameBoard.getBoard()[2][1] == 'O' && this.gameBoard.getBoard()[2][2] == 'O') {
            System.out.println("O wins\n");
            return true;
        }

        if (this.gameBoard.getBoard()[0][0] == 'X' && this.gameBoard.getBoard()[1][0] == 'X' && this.gameBoard.getBoard()[2][0] == 'X') {
            System.out.println("X wins\n");
            return true;
        }

        if (this.gameBoard.getBoard()[0][1] == 'X' && this.gameBoard.getBoard()[1][1] == 'X' && this.gameBoard.getBoard()[2][1] == 'X') {
            System.out.println("X wins\n");
            return true;
        }

        if (this.gameBoard.getBoard()[0][2] == 'X' && this.gameBoard.getBoard()[1][2] == 'X' && this.gameBoard.getBoard()[2][2] == 'X') {
            System.out.println("X wins\n");
            return true;
        }

        if (this.gameBoard.getBoard()[0][0] == 'O' && this.gameBoard.getBoard()[1][0] == 'O' && this.gameBoard.getBoard()[2][0] == 'O') {
            System.out.println("O wins\n");
            return true;
        }

        if (this.gameBoard.getBoard()[0][1] == 'O' && this.gameBoard.getBoard()[1][1] == 'O' && this.gameBoard.getBoard()[2][1] == 'O') {
            System.out.println("O wins\n");
            return true;
        }

        if (this.gameBoard.getBoard()[0][2] == 'O' && this.gameBoard.getBoard()[1][2] == 'O' && this.gameBoard.getBoard()[2][2] == 'O') {
            System.out.println("O wins\n");
            return true;
        }

        if (this.gameBoard.getBoard()[0][0] == 'X' && this.gameBoard.getBoard()[1][1] == 'X' && this.gameBoard.getBoard()[2][2] == 'X') {
            System.out.println("X wins\n");
            return true;
        }

        if (this.gameBoard.getBoard()[0][2] == 'X' && this.gameBoard.getBoard()[1][1] == 'X' && this.gameBoard.getBoard()[2][0] == 'X') {
            System.out.println("X wins\n");
            return true;
        }

        if (this.gameBoard.getBoard()[0][0] == 'O' && this.gameBoard.getBoard()[1][1] == 'O' && this.gameBoard.getBoard()[2][2] == 'O') {
            System.out.println("O wins\n");
            return true;
        }

        if (this.gameBoard.getBoard()[0][2] == 'O' && this.gameBoard.getBoard()[1][1] == 'O' && this.gameBoard.getBoard()[2][0] == 'O') {
            System.out.println("O wins\n");
            return true;
        }

        if (this.gameBoard.getFilledFields() == 9) {
            System.out.println("Draw");
            return true;
        }
        return false;
    }
}
