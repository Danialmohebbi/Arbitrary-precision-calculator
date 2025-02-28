package cz.cuni.mohebbis.Program;

import cz.cuni.mohebbis.view.View;

import java.io.IOException;

/**
 * The main method serves as the entry point for the application.
 *
 * @throws IOException If there are issues with file handling or input/output during execution.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Controller controller = new Controller();
        View view = new View(controller);
        view.processInput();
    }
}
