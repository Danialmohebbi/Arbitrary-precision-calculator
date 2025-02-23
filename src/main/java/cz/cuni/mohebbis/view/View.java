package cz.cuni.mohebbis.view;

import cz.cuni.mohebbis.Program.Controller;

import java.util.Scanner;

public class View {
    private Controller controller;

    public View(Controller controller) {
        this.controller = controller;
    }

    public void processInput() {
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                System.out.print("Enter expression (or 'exit' to quit): ");
                if (!scanner.hasNextLine()) {
                    break;
                }

                String input = scanner.nextLine().trim();

                if (input.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting...");
                    break;
                }

                if (input.startsWith("=")) {
                    String expression = input.substring(1).trim();
                    try {
                        int outputResult = controller.ProcessInput(expression, this);
                        System.out.println("Result: " + outputResult);
                    } catch (Exception e) {
                        System.out.println("Error processing input: " + e.getMessage());
                    }
                }
                else {
                    System.out.println("Incorrect command. Please start your input with '='.");
                }
            }
        } finally {
            scanner.close();
        }
    }
}