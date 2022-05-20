package tictactoe.gameengine;

import tictactoe.playerconstants.PlayerTurnEnums;
import tictactoe.components.Ai;
import tictactoe.components.GameGrid;

import java.util.Scanner;

public class GameBoard {
    private final Scanner scanner;
    private GameGrid gameGrid;
    private char playerTurn;
    private Ai artificialPlayer;

    public GameBoard(GameGrid gameGrid, Ai artificialPlayer) {
        this.scanner = new Scanner(System.in);
        this.gameGrid = gameGrid;
        gameGrid.initGrid();
        this.playerTurn = PlayerTurnEnums.PLAYER_X.getTurn();
        this.artificialPlayer = artificialPlayer;
    }

    public void start() {
        while (true) {
            this.gameGrid.printGrid();
            this.checkGameState();

            if (this.playerTurn == PlayerTurnEnums.PLAYER_X.getTurn()) {
                this.turnForX();
            } else {
                this.turnForO();
            }
            this.gameGrid.increaseNumberOfFields();
        }
    }

    public void turnForX() {
        System.out.println("Enter the coordinates: ");

        int[] coordinates = this.checkInput();
        this.checkSpotAndMakeMove(coordinates);
    }

    public void turnForO() {
        this.changePlayer(PlayerTurnEnums.PLAYER_X.getTurn());
        System.out.println("Making move level \"easy\"");

        this.makeMoveIfPossible();
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
            this.gameGrid.setBoard(firstCoordinate, secondCoordinate, PlayerTurnEnums.PLAYER_O.getTurn());
        } else {
            this.makeMoveIfPossible();
        }
    }

    public void checkSpotAndMakeMove(int[] coordinates) {
        if (this.gameGrid.getBoard()[coordinates[0]][coordinates[1]] != '_') {
            this.printErrorAndAskAgain();
        } else {
            this.gameGrid.setBoard(coordinates[0], coordinates[1], PlayerTurnEnums.PLAYER_X.getTurn());
        }
        this.changePlayer(PlayerTurnEnums.PLAYER_O.getTurn());
    }

    public void printErrorAndAskAgain() {
        System.out.println("This cell is occupied! Choose another one!");
        this.turnForX();
    }

    public void changePlayer(char turn) {
        this.playerTurn = turn;
    }

    public void checkGameState() {
        if (this.gameGrid.getFilledFields() == 9) {
            System.out.println("Draw");
            System.exit(0);
        }

        if (this.gameGrid.getBoard()[0][0] == 'X' && this.gameGrid.getBoard()[0][1] == 'X' && this.gameGrid.getBoard()[0][2] == 'X') {
            System.out.println("X wins");
            System.exit(0);
        }

        if (this.gameGrid.getBoard()[1][0] == 'X' && this.gameGrid.getBoard()[1][1] == 'X' && this.gameGrid.getBoard()[1][2] == 'X') {
            System.out.println("X wins");
            System.exit(0);
        }

        if (this.gameGrid.getBoard()[2][0] == 'X' && this.gameGrid.getBoard()[2][1] == 'X' && this.gameGrid.getBoard()[2][2] == 'X') {
            System.out.println("X wins");
            System.exit(0);
        }

        if (this.gameGrid.getBoard()[0][0] == 'O' && this.gameGrid.getBoard()[0][1] == 'O' && this.gameGrid.getBoard()[0][2] == 'O') {
            System.out.println("O wins");
            System.exit(0);
        }

        if (this.gameGrid.getBoard()[1][0] == 'O' && this.gameGrid.getBoard()[1][1] == 'O' && this.gameGrid.getBoard()[1][2] == 'O') {
            System.out.println("O wins");
            System.exit(0);
        }

        if (this.gameGrid.getBoard()[2][0] == 'O' && this.gameGrid.getBoard()[2][1] == 'O' && this.gameGrid.getBoard()[2][2] == 'O') {
            System.out.println("O wins");
            System.exit(0);
        }

        if (this.gameGrid.getBoard()[0][0] == 'X' && this.gameGrid.getBoard()[1][0] == 'X' && this.gameGrid.getBoard()[2][0] == 'X') {
            System.out.println("X wins");
            System.exit(0);
        }

        if (this.gameGrid.getBoard()[0][1] == 'X' && this.gameGrid.getBoard()[1][1] == 'X' && this.gameGrid.getBoard()[2][1] == 'X') {
            System.out.println("X wins");
            System.exit(0);
        }

        if (this.gameGrid.getBoard()[0][2] == 'X' && this.gameGrid.getBoard()[1][2] == 'X' && this.gameGrid.getBoard()[2][2] == 'X') {
            System.out.println("X wins");
            System.exit(0);
        }

        if (this.gameGrid.getBoard()[0][0] == 'O' && this.gameGrid.getBoard()[1][0] == 'O' && this.gameGrid.getBoard()[2][0] == 'O') {
            System.out.println("O wins");
            System.exit(0);
        }

        if (this.gameGrid.getBoard()[0][1] == 'O' && this.gameGrid.getBoard()[1][1] == 'O' && this.gameGrid.getBoard()[2][1] == 'O') {
            System.out.println("O wins");
            System.exit(0);
        }

        if (this.gameGrid.getBoard()[0][2] == 'O' && this.gameGrid.getBoard()[1][2] == 'O' && this.gameGrid.getBoard()[2][2] == 'O') {
            System.out.println("O wins");
            System.exit(0);
        }

        if (this.gameGrid.getBoard()[0][0] == 'X' && this.gameGrid.getBoard()[1][1] == 'X' && this.gameGrid.getBoard()[2][2] == 'X') {
            System.out.println("X wins");
            System.exit(0);
        }

        if (this.gameGrid.getBoard()[0][2] == 'X' && this.gameGrid.getBoard()[1][1] == 'X' && this.gameGrid.getBoard()[2][0] == 'X') {
            System.out.println("X wins");
            System.exit(0);
        }

        if (this.gameGrid.getBoard()[0][0] == 'O' && this.gameGrid.getBoard()[1][1] == 'O' && this.gameGrid.getBoard()[2][2] == 'O') {
            System.out.println("O wins");
            System.exit(0);
        }

        if (this.gameGrid.getBoard()[0][2] == 'O' && this.gameGrid.getBoard()[1][1] == 'O' && this.gameGrid.getBoard()[2][0] == 'O') {
            System.out.println("O wins");
            System.exit(0);
        }
    }
}
