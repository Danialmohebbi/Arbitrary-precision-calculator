package cz.cuni.mohebbis.view;

import cz.cuni.mohebbis.Program.Controller;
import cz.cuni.mohebbis.logic.numbers.Integer;
import cz.cuni.mohebbis.logic.numbers.Natural;
import cz.cuni.mohebbis.logic.numbers.Rational;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class View {
    private Controller controller;
    /**
     * Constructor to initialize the View with the given controller.
     *
     * @param controller The controller instance to interact with the logic layer.
     */
    public View(Controller controller) {
        this.controller = controller;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt"))) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Main method to handle user input and process commands.
     * It continuously takes user input, processes it and calls appropriate controller methods.
     */
    public void processInput() {
        Scanner scanner = new Scanner(System.in);
        try {

            String type = "";
            while (true) {
                System.out.print("Enter expression (or 'exit' to quit): ");
                if (!scanner.hasNextLine()) {
                    break;
                }

                String input = scanner.nextLine().trim();

                if (input.equalsIgnoreCase("exit")) {
                    controller.logInfo("in: " + input);
                    System.out.println("Exiting...");
                    controller.logInfo("out: " + "Exiting...");
                    break;
                }

                if (input.startsWith("Type: ")) {
                    type = input.split("\\s+")[1];
                    controller.logInfo("in: " + input);
                    continue;
                }

                if (input.startsWith("=")) {
                    try {
                        ExecuteCorrectType(type,input);
                        controller.logInfo("in: " + input);
                    } catch (Exception e) {
                        System.out.println("Error processing input: " + e.getMessage());
                        controller.logInfo("in: " + input);
                        controller.logInfo("out: " + "Error processing input: " + e.getMessage());
                    }
                }else if (input.startsWith("Process File: ")) {
                    controller.logInfo("in: " + input);
                    String path = input.split("\\s+")[2].trim();
                    type = input.split("\\s+")[3].trim();
                    Path filePath = Paths.get(path);
                    if (!Files.exists(filePath)) {
                        System.out.println("File does not exist: " + path);
                        controller.logInfo("out: " + "File does not exist: " + path);
                    }
                    controller.ProcessFile(filePath,type);
                    type = "";
                }
                else {
                    controller.logInfo("in: " + input);
                    System.out.println("Incorrect command. Please start your input with '='.");
                    controller.logInfo("out: " + "Incorrect command. Please start your input with '='.");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            scanner.close();
        }
    }

    /**
     * Executes the correct operation based on the given type and input expression.
     *
     * @param type The type of number expressions (int, natural, integer, rational).
     * @param input The mathematical expression to evaluate.
     * @throws IOException If there is an error processing the input.
     */
    private void ExecuteCorrectType(String type,String input) throws IOException {
        if (type.equals("int")) {
            int outputResult = controller.ProcessIntInput(input);
            System.out.println("Result: " + outputResult);
            controller.logInfo("out: " + "Result: " + outputResult);
        }else if (type.equals("natural")) {
            Natural outputResult = controller.ProcessNaturalInput(input);
            System.out.println("Result: " + outputResult.ToString());
            controller.logInfo("out: " + "Result: " + outputResult.ToString());
        }else if (type.equals("integer")) {
            Integer outputResult = controller.ProcessIntegerInput(input);
            System.out.println("Result: " + outputResult.ToString());
            controller.logInfo("out: " + "Result: " + outputResult.ToString());
        }else if (type.equals("rational")) {
            Rational outputResult = controller.ProcessRationalInput(input);
            System.out.println("Result: " + outputResult.ToString());
            controller.logInfo("out: " + "Result: " + outputResult.ToString());
        }else if (type.equals("float")) {
            Float outputResult = controller.ProcessFloatInput(input);
            System.out.println("Result: " + outputResult);
            controller.logInfo("out: " + "Result: " + outputResult);
        }else if (type.equals("double")) {
            Double outputResult = controller.ProcessDoubleInput(input);
            System.out.println("Result: " + outputResult);
            controller.logInfo("out: "+  "Result: " + outputResult);
        }

    }
}