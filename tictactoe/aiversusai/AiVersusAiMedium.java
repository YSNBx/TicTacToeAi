package tictactoe.aiversusai;

import tictactoe.ui.SuperInterface;
import tictactoe.components.Ai;
import tictactoe.components.GameBoard;
import tictactoe.playerconstants.PlayerTurnEnums;

public class AiVersusAiMedium implements SuperInterface {
    private GameBoard gameBoard;
    private char playerTurn;
    private Ai artificialPlayer;

    public AiVersusAiMedium(GameBoard gameBoard, Ai artificialPlayer) {
        this.gameBoard = gameBoard;
        gameBoard.initGrid();
        this.playerTurn = PlayerTurnEnums.PLAYER_X.getTurn();
        this.artificialPlayer = artificialPlayer;
    }

    @Override
    public void start() {
        this.makeMoveIfPossible();
        this.gameBoard.increaseNumberOfFields();
        this.changePlayer();

        while (true) {
            this.gameBoard.printGrid();

            if (this.isGameFinished()) {
                break;
            }

            this.checkTurnAndMakeMove();
            this.gameBoard.increaseNumberOfFields();
        }
    }

    public void checkTurnAndMakeMove() {
        if (this.playerTurn == PlayerTurnEnums.PLAYER_X.getTurn()) {
            this.turnForX();
            this.changePlayer();
        } else {
            this.turnForO();
            this.changePlayer();
        }
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

    public void turnForO() {
        System.out.println("Making move level \"medium\"");
        this.evaluateBestMove();
    }

    public void turnForX() {
        System.out.println("Making move level \"medium\"");
        this.evaluateBestMove();
    }

    public void evaluateBestMove() {
        int bestScore = -9999;
        int[] move = new int[2];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.gameBoard.getBoard()[i][j] == '_') {
                    this.gameBoard.getBoard()[i][j] = this.playerTurn;
                    this.gameBoard.increaseNumberOfFields();
                    int score = minimax(this.gameBoard.getBoard(), 1, false);
                    this.gameBoard.getBoard()[i][j] = '_';
                    this.gameBoard.decreaseNumberOfFields();
                    if (score > bestScore) {
                        bestScore = score;
                        move[0] = i;
                        move[1] = j;
                    }
                }
            }
        }
        this.gameBoard.getBoard()[move[0]][move[1]] = this.playerTurn;
    }

    public boolean checkDraw() {
        return this.gameBoard.getFilledFields() == 9;
    }

    public int minimax(char[][] gameBoard, int depth, boolean isMaximizing) {
        if (this.checkWhoWon(PlayerTurnEnums.PLAYER_O.getTurn())) {
            return 1;
        } else if (this.checkWhoWon(PlayerTurnEnums.PLAYER_X.getTurn())) {
            return -1;
        } else if (this.checkDraw()) {
            return 0;
        }

        if (isMaximizing) {
            int bestScore = -99999;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (this.gameBoard.getBoard()[i][j] == '_') {
                        this.gameBoard.getBoard()[i][j] = PlayerTurnEnums.PLAYER_O.getTurn();
                        this.gameBoard.increaseNumberOfFields();
                        int score = minimax(this.gameBoard.getBoard(), depth + 1, false);
                        this.gameBoard.getBoard()[i][j] = '_';
                        this.gameBoard.decreaseNumberOfFields();
                        if (score > bestScore) {
                            bestScore = score;
                        }
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = 99999;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (this.gameBoard.getBoard()[i][j] == '_') {
                        this.gameBoard.getBoard()[i][j] = PlayerTurnEnums.PLAYER_X.getTurn();
                        this.gameBoard.increaseNumberOfFields();
                        int score = minimax(this.gameBoard.getBoard(), depth + 1, true);
                        this.gameBoard.getBoard()[i][j] = '_';
                        this.gameBoard.decreaseNumberOfFields();
                        if (score < bestScore) {
                            bestScore = score;
                        }
                    }
                }
            }
            return bestScore;
        }
    }

    public void changePlayer() {
        if (this.playerTurn == PlayerTurnEnums.PLAYER_X.getTurn()) {
            this.playerTurn = PlayerTurnEnums.PLAYER_O.getTurn();
        } else {
            this.playerTurn = PlayerTurnEnums.PLAYER_X.getTurn();
        }
    }

    public boolean checkWhoWon(char mark) {
        if (this.gameBoard.getBoard()[0][0] == mark && this.gameBoard.getBoard()[0][1] == mark && this.gameBoard.getBoard()[0][2] == mark) {
            return true;
        }

        if (this.gameBoard.getBoard()[1][0] == mark && this.gameBoard.getBoard()[1][1] == mark && this.gameBoard.getBoard()[1][2] == mark) {
            return true;
        }

        if (this.gameBoard.getBoard()[2][0] == mark && this.gameBoard.getBoard()[2][1] == mark && this.gameBoard.getBoard()[2][2] == mark) {
            return true;
        }

        if (this.gameBoard.getBoard()[0][0] == mark && this.gameBoard.getBoard()[1][0] == mark && this.gameBoard.getBoard()[2][0] == mark) {
            return true;
        }

        if (this.gameBoard.getBoard()[0][1] == mark && this.gameBoard.getBoard()[1][1] == mark && this.gameBoard.getBoard()[2][1] == mark) {
            return true;
        }

        if (this.gameBoard.getBoard()[0][2] == mark && this.gameBoard.getBoard()[1][2] == mark && this.gameBoard.getBoard()[2][2] == mark) {
            return true;
        }

        if (this.gameBoard.getBoard()[0][0] == mark && this.gameBoard.getBoard()[1][1] == mark && this.gameBoard.getBoard()[2][2] == mark) {
            return true;
        }

        if (this.gameBoard.getBoard()[0][2] == mark && this.gameBoard.getBoard()[1][1] == mark && this.gameBoard.getBoard()[2][0] == mark) {
            return true;
        }

        return false;
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
