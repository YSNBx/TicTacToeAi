package tictactoe.gameengine;

import java.util.Scanner;

import tictactoe.components.Ai;
import tictactoe.components.GameGrid;
import tictactoe.components.InterfaceController;

public class InitGame {
    private InterfaceController interfaceController;
    private final Scanner scanner;

    public InitGame() {
        this.interfaceController = new InterfaceController();
        this.scanner = new Scanner(System.in);
    }

    public void init() {
        while (true) {
            System.out.println("Input command: ");
            String command = scanner.nextLine();

            if (command.equals("exit")) {
                break;
            }

            this.evaluateCommand(command);
        }
    }

    public void evaluateCommand(String command) {
        if (command.equals("start user easy")) {
            this.executePlayerFirst();
        } else if (command.equals("start easy user")) {
            this.executeAiFirst();
        } else if (command.equals("start easy easy")) {
            this.executeAiVsAi();
        } else {
            System.out.println("Bad parameters!");
            this.init();
        }
    }

    public void executePlayerFirst() {
        Ai ai = new Ai();
        GameGrid grid = new GameGrid();

        this.interfaceController.setInterface(new PlayerVersusAi(grid, ai));
        this.interfaceController.execute();
    }

    public void executeAiFirst() {
        Ai ai = new Ai();
        GameGrid grid = new GameGrid();

        this.interfaceController.setInterface(new AiVersusPlayer(grid, ai));
        this.interfaceController.execute();
    }

    public void executeAiVsAi() {
        Ai ai = new Ai();
        GameGrid grid = new GameGrid();
        
        this.interfaceController.setInterface(new AiVersusAi(grid, ai));
        this.interfaceController.execute();
    }
}
