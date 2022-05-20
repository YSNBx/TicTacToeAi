package tictactoe.playerconstants;

public enum PlayerTurnEnums {
    PLAYER_O('O'),
    PLAYER_X('X');

    private char turn;

    PlayerTurnEnums(char turn) {
        this.turn = turn;
    }

    public char getTurn() {
        return this.turn;
    }
}
