package core.basesyntax;

import core.basesyntax.controler.ConsoleHandler;

public class Main {
    public static void main(String[] args) {
        ConsoleHandler consoleHandler = new ConsoleHandler();
        System.out.println("Enter value and risk");
        consoleHandler.userHandler();
    }
}
