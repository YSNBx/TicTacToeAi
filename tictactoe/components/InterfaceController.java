package tictactoe.components;

import tictactoe.ui.SuperInterface;

public class InterfaceController {
    private SuperInterface superInterface;

    public void setInterface(SuperInterface superInterface) {
        this.superInterface = superInterface;
    }

    public void execute() {
        this.superInterface.start();
    }
}
