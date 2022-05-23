package tictactoe.components;

import java.util.Random;

public class Ai {
    private int[] coordinates;
    private Random random;

    public Ai() {
        this.coordinates = new int[2];
        this.random = new Random();
    }

    public void setCoordinates(int firstCoordinate, int secondCoordinate) {
        this.coordinates[0] = firstCoordinate;
        this.coordinates[1] = secondCoordinate;
    }
    
    public int[] getCoordinates() {
        return this.coordinates;
    }

    public int[] generateCoordinates() {
        this.coordinates[0] = random.nextInt(3);
        this.coordinates[1] = random.nextInt(3);

        return this.coordinates;
    }

}
