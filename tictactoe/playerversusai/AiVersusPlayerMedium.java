package tictactoe.playerversusai;


import tictactoe.ui.SuperInterface;
import tictactoe.components.Ai;
import tictactoe.components.GameBoard;
import tictactoe.playerconstants.PlayerTurnEnums;

import java.util.Scanner;

public class AiVersusPlayerMedium implements SuperInterface {
    private final GameBoard gameGrid;
    private char playerTurn;
    private final Ai artificialPlayer;
    private final Scanner scanner;

    public AiVersusPlayerMedium(GameBoard gameGrid, Ai artificialPlayer) {
        this.gameGrid = gameGrid;
        gameGrid.initGrid();
        this.playerTurn = PlayerTurnEnums.PLAYER_X.getTurn();
        this.artificialPlayer = artificialPlayer;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void start() {
        while (true) {
            this.gameGrid.printGrid();

            if (this.isGameFinished()) {
                break;
            }

            this.checkTurnAndMakeMove();
            this.gameGrid.increaseNumberOfFields();
        }
    }

    public void firstAiMove() {
        this.makeMoveIfPossible();
        this.gameGrid.increaseNumberOfFields();
        this.changePlayer();
    }

    public void checkTurnAndMakeMove() {
        if (this.playerTurn == PlayerTurnEnums.PLAYER_X.getTurn()) {
            this.aiMove();
            this.changePlayer();
        } else {
            this.playerMove();
            this.changePlayer();
        }
    }

    public void playerMove() {
        System.out.println("Enter the coordinates: (e.g. \"1 3\", with a space inbetween");

        int[] coordinates = this.checkInput();
        this.checkSpotAndMakeMove(coordinates);
    }

    public void aiMove() {
        System.out.println("Making move level \"medium\"");
        this.evaluateBestMove();
    }

    public void evaluateBestMove() {
        int bestScore = -999999;
        int[] move = new int[2];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.gameGrid.getBoard()[i][j] == '_') {
                    this.gameGrid.getBoard()[i][j] = this.playerTurn;
                    this.gameGrid.increaseNumberOfFields();
                    int score = minimax(this.gameGrid.getBoard(), 0, false);
                    this.gameGrid.getBoard()[i][j] = '_';
                    this.gameGrid.decreaseNumberOfFields();
                    if (score > bestScore) {
                        bestScore = score;
                        move[0] = i;
                        move[1] = j;
                    }
                }
            }
        }
        this.gameGrid.getBoard()[move[0]][move[1]] = this.playerTurn;
    }

    public boolean checkDraw() {
        return this.gameGrid.getFilledFields() == 8;
    }

    public int minimax(char[][] gameGrid, int depth, boolean isMaximizing) {
        if (this.checkWhoWon(PlayerTurnEnums.PLAYER_O.getTurn())) {
            return 100;
        } else if (this.checkWhoWon(PlayerTurnEnums.PLAYER_X.getTurn())) {
            return -100;
        } else if (this.checkDraw()) {
            return 0;
        }

        if (isMaximizing) {
            int bestScore = -99999;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (this.gameGrid.getBoard()[i][j] == '_') {
                        this.gameGrid.getBoard()[i][j] = PlayerTurnEnums.PLAYER_O.getTurn();
                        this.gameGrid.increaseNumberOfFields();
                        int score = minimax(this.gameGrid.getBoard(), depth + 1, true);
                        this.gameGrid.getBoard()[i][j] = '_';
                        this.gameGrid.decreaseNumberOfFields();
                        bestScore = Math.max(bestScore, score);
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = 99999;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (this.gameGrid.getBoard()[i][j] == '_') {
                        this.gameGrid.getBoard()[i][j] = PlayerTurnEnums.PLAYER_X.getTurn();
                        this.gameGrid.increaseNumberOfFields();
                        int score = minimax(this.gameGrid.getBoard(), depth + 1, false);
                        this.gameGrid.getBoard()[i][j] = '_';
                        this.gameGrid.decreaseNumberOfFields();
                        bestScore = Math.min(bestScore, score);
                    }
                }
            }
            return bestScore;
        }
    }

    public int[] checkInput() {
        String[] input = scanner.nextLine().split("\\s");
        int[] coordinates = new int[2];

        try {
            coordinates[0] = Integer.parseInt(input[0]) - 1;
            coordinates[1] = Integer.parseInt(input[1]) - 1;
        } catch (NumberFormatException e) {
            System.out.println("You should enter numbers!");
            this.checkInput();
        }

        return coordinates;
    }

    public void makeMoveIfPossible() {
        int[] coordinates = this.artificialPlayer.generateCoordinates();
        int firstCoordinate = coordinates[0];
        int secondCoordinate = coordinates[1];

        if (this.gameGrid.getBoard()[firstCoordinate][secondCoordinate] == '_') {
            this.gameGrid.setBoard(firstCoordinate, secondCoordinate, this.playerTurn);
        } else {
            this.makeMoveIfPossible();
        }
    }

    public void checkSpotAndMakeMove(int[] coordinates) {
        if (this.gameGrid.getBoard()[coordinates[0]][coordinates[1]] != '_') {
            this.printErrorAndAskAgain();
        } else {
            this.gameGrid.setBoard(coordinates[0], coordinates[1], this.playerTurn);
        }
    }

    public void printErrorAndAskAgain() {
        System.out.println("This cell is occupied! Choose another one!");
        this.playerMove();
    }

    public void changePlayer() {
        if (this.playerTurn == PlayerTurnEnums.PLAYER_X.getTurn()) {
            this.playerTurn = PlayerTurnEnums.PLAYER_O.getTurn();
        } else {
            this.playerTurn = PlayerTurnEnums.PLAYER_X.getTurn();
        }
    }

    public boolean checkWhoWon(char mark) {
        if (this.gameGrid.getBoard()[0][0] == mark && this.gameGrid.getBoard()[0][1] == mark && this.gameGrid.getBoard()[0][2] == mark) {
            return true;
        }

        if (this.gameGrid.getBoard()[1][0] == mark && this.gameGrid.getBoard()[1][1] == mark && this.gameGrid.getBoard()[1][2] == mark) {
            return true;
        }

        if (this.gameGrid.getBoard()[2][0] == mark && this.gameGrid.getBoard()[2][1] == mark && this.gameGrid.getBoard()[2][2] == mark) {
            return true;
        }

        if (this.gameGrid.getBoard()[0][0] == mark && this.gameGrid.getBoard()[1][0] == mark && this.gameGrid.getBoard()[2][0] == mark) {
            return true;
        }

        if (this.gameGrid.getBoard()[0][1] == mark && this.gameGrid.getBoard()[1][1] == mark && this.gameGrid.getBoard()[2][1] == mark) {
            return true;
        }

        if (this.gameGrid.getBoard()[0][2] == mark && this.gameGrid.getBoard()[1][2] == mark && this.gameGrid.getBoard()[2][2] == mark) {
            return true;
        }

        if (this.gameGrid.getBoard()[0][0] == mark && this.gameGrid.getBoard()[1][1] == mark && this.gameGrid.getBoard()[2][2] == mark) {
            return true;
        }

        if (this.gameGrid.getBoard()[0][2] == mark && this.gameGrid.getBoard()[1][1] == mark && this.gameGrid.getBoard()[2][0] == mark) {
            return true;
        }

        if (this.gameGrid.getFilledFields() == 9) {
            return true;
        }
        return false;
    }

    //first quick version of isGameFinished

    public boolean isGameFinished() {
        if (this.gameGrid.getBoard()[0][0] == 'X' && this.gameGrid.getBoard()[0][1] == 'X' && this.gameGrid.getBoard()[0][2] == 'X') {
            System.out.println("X wins\n");
            return true;
        }

        if (this.gameGrid.getBoard()[1][0] == 'X' && this.gameGrid.getBoard()[1][1] == 'X' && this.gameGrid.getBoard()[1][2] == 'X') {
            System.out.println("X wins\n");
            return true;
        }

        if (this.gameGrid.getBoard()[2][0] == 'X' && this.gameGrid.getBoard()[2][1] == 'X' && this.gameGrid.getBoard()[2][2] == 'X') {
            System.out.println("X wins\n");
            return true;
        }

        if (this.gameGrid.getBoard()[0][0] == 'O' && this.gameGrid.getBoard()[0][1] == 'O' && this.gameGrid.getBoard()[0][2] == 'O') {
            System.out.println("O wins\n");
            return true;
        }

        if (this.gameGrid.getBoard()[1][0] == 'O' && this.gameGrid.getBoard()[1][1] == 'O' && this.gameGrid.getBoard()[1][2] == 'O') {
            System.out.println("O wins\n");
            return true;
        }

        if (this.gameGrid.getBoard()[2][0] == 'O' && this.gameGrid.getBoard()[2][1] == 'O' && this.gameGrid.getBoard()[2][2] == 'O') {
            System.out.println("O wins\n");
            return true;
        }

        if (this.gameGrid.getBoard()[0][0] == 'X' && this.gameGrid.getBoard()[1][0] == 'X' && this.gameGrid.getBoard()[2][0] == 'X') {
            System.out.println("X wins\n");
            return true;
        }

        if (this.gameGrid.getBoard()[0][1] == 'X' && this.gameGrid.getBoard()[1][1] == 'X' && this.gameGrid.getBoard()[2][1] == 'X') {
            System.out.println("X wins\n");
            return true;
        }

        if (this.gameGrid.getBoard()[0][2] == 'X' && this.gameGrid.getBoard()[1][2] == 'X' && this.gameGrid.getBoard()[2][2] == 'X') {
            System.out.println("X wins\n");
            return true;
        }

        if (this.gameGrid.getBoard()[0][0] == 'O' && this.gameGrid.getBoard()[1][0] == 'O' && this.gameGrid.getBoard()[2][0] == 'O') {
            System.out.println("O wins\n");
            return true;
        }

        if (this.gameGrid.getBoard()[0][1] == 'O' && this.gameGrid.getBoard()[1][1] == 'O' && this.gameGrid.getBoard()[2][1] == 'O') {
            System.out.println("O wins\n");
            return true;
        }

        if (this.gameGrid.getBoard()[0][2] == 'O' && this.gameGrid.getBoard()[1][2] == 'O' && this.gameGrid.getBoard()[2][2] == 'O') {
            System.out.println("O wins\n");
            return true;
        }

        if (this.gameGrid.getBoard()[0][0] == 'X' && this.gameGrid.getBoard()[1][1] == 'X' && this.gameGrid.getBoard()[2][2] == 'X') {
            System.out.println("X wins\n");
            return true;
        }

        if (this.gameGrid.getBoard()[0][2] == 'X' && this.gameGrid.getBoard()[1][1] == 'X' && this.gameGrid.getBoard()[2][0] == 'X') {
            System.out.println("X wins\n");
            return true;
        }

        if (this.gameGrid.getBoard()[0][0] == 'O' && this.gameGrid.getBoard()[1][1] == 'O' && this.gameGrid.getBoard()[2][2] == 'O') {
            System.out.println("O wins\n");
            return true;
        }

        if (this.gameGrid.getBoard()[0][2] == 'O' && this.gameGrid.getBoard()[1][1] == 'O' && this.gameGrid.getBoard()[2][0] == 'O') {
            System.out.println("O wins\n");
            return true;
        }

        if (this.gameGrid.getFilledFields() == 9) {
            System.out.println("Draw");
            return true;
        }
        return false;
    }
}
