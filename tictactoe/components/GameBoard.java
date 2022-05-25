package tictactoe.components;

public class GameBoard {
    private char[][] board;
    private int filledFields;

    public GameBoard() {
        this.board = new char[3][3];
        this.filledFields = 0;
    }

    public void initGrid() {
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board.length; j++) {
                this.board[i][j] = '_';
            }
        }
    }

    public void printGrid() {
        System.out.println("_________");
        for (char[] chars : this.board) {
            System.out.print("| ");
            for (int j = 0; j < this.board.length; j++) {
                System.out.print(chars[j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public char[][] getBoard() {
        return this.board;
    }

    public void setBoard(int firstCoordinate, int secondCoordinate, char player) {
        this.board[firstCoordinate][secondCoordinate] = player;
    }

    public void increaseNumberOfFields() {
        this.filledFields++;
    }

    public void decreaseNumberOfFields() {
        this.filledFields--;
    }

    public int getFilledFields() {
        return this.filledFields;
    }
}
