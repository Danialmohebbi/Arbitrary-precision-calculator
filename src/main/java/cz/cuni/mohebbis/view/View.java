package cz.cuni.mohebbis.view;

import cz.cuni.mohebbis.Program.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
                    try {
                        int outputResult = controller.ProcessInput(input, this);
                        System.out.println("Result: " + outputResult);
                    } catch (Exception e) {
                        System.out.println("Error processing input: " + e.getMessage());
                    }
                }else if (input.startsWith("Process File: ")) {
                    String path = input.split("\\s+")[2].trim();
                    Path filePath = Paths.get(path);
                    if (!Files.exists(filePath)) {
                        System.out.println("File does not exist: " + path);
                    }
                    controller.ProcessFile(filePath,this);
                }
                else {
                    System.out.println("Incorrect command. Please start your input with '='.");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            scanner.close();
        }
    }
}