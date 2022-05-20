package tictactoe.gameengine;

import java.util.Scanner;

import tictactoe.components.Ai;
import tictactoe.components.GameGrid;
import tictactoe.components.InterfaceController;

public class ChooseGame {
    private String choice;
    private InterfaceController interfaceController;
    private final Scanner scanner;

    public ChooseGame(Scanner scanner) {
        this.scanner = scanner;
        this.choice = initChoice();
        this.interfaceController = new InterfaceController();
    }

    public String initChoice() {
        System.out.println("Enter game mode: ");
        return this.scanner.nextLine();
/*
        while (true) {
            System.out.println("Choose game type: ");    
            this.choice = scanner.nextLine();

            if (this.choice.equalsIgnoreCase("exit")) {
                break;
            }
        }
*/
    }

    public void executeProgram() {
        Ai ai = new Ai();
        GameGrid grid = new GameGrid();

        this.interfaceController.setInterface(new PlayerVersusAi(grid, ai, this.scanner));
        this.interfaceController.execute();
    }
}
