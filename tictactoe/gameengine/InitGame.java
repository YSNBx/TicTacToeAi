package tictactoe.gameengine;

import java.util.Scanner;

import tictactoe.aiversusai.AiVersusAiEasy;
import tictactoe.aiversusai.AiVersusAiMedium;
import tictactoe.components.Ai;
import tictactoe.components.GameBoard;
import tictactoe.playerversusai.AiVersusPlayerEasy;
import tictactoe.playerversusai.AiVersusPlayerMedium;
import tictactoe.playerversusai.PlayerVersusAiEasy;
import tictactoe.playerversusai.PlayerVersusAiMedium;
import tictactoe.ui.InterfaceController;
import tictactoe.ui.SuperInterface;

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
            this.setGameMode(command);
        }
    }

    public void setGameMode(String command) {
        if (command.equals("start user easy")) {
            this.executePlayerFirst(new PlayerVersusAiEasy(new GameBoard(), new Ai()));
        } else if (command.equals("start user medium")) {
            this.executePlayerFirst(new PlayerVersusAiMedium(new GameBoard(), new Ai()));
        } else if (command.equals("start easy user")) {
            this.executeAiFirst(new AiVersusPlayerEasy(new GameBoard(), new Ai()));
        } else if (command.equals("start medium user")) {
            this.executeAiFirst(new AiVersusPlayerMedium(new GameBoard(), new Ai()));
        } else if (command.equals("start easy easy")) {
            this.executeAiVsAi(new AiVersusAiEasy(new GameBoard(), new Ai()));
        } else if (command.equals("start medium medium")) {
            this.executeAiVsAi(new AiVersusAiMedium(new GameBoard(), new Ai()));
        } else {
            System.out.println("Bad parameters!");
        }
    }


    public void executePlayerFirst(SuperInterface gameMode) {
        this.interfaceController.setInterface(gameMode);
        this.interfaceController.execute();
    }

    public void executeAiFirst(SuperInterface gameMode) {
        this.interfaceController.setInterface(gameMode);
        this.interfaceController.execute();
    }

    public void executeAiVsAi(SuperInterface gameMode) {
        this.interfaceController.setInterface(gameMode);
        this.interfaceController.execute();
    }
}
