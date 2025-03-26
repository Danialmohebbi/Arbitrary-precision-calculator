package cz.cuni.mohebbis.Program;

import cz.cuni.mohebbis.logic.parsers.InfixExpressionParser;
import cz.cuni.mohebbis.logic.interfaces.*;
import cz.cuni.mohebbis.logic.exceptions.DivisionByZeroException;
import cz.cuni.mohebbis.logic.exceptions.FormatException;
import cz.cuni.mohebbis.logic.numbers.Natural;
import cz.cuni.mohebbis.logic.numbers.Integer;
import cz.cuni.mohebbis.logic.numbers.Rational;
import cz.cuni.mohebbis.logic.visitors.*;
import cz.cuni.mohebbis.view.View;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;



public class Controller {
    /**
     * Process input string as a java.lang.int expression and returns the evaluated result.
     *
     * @param input The string containing the mathematical expression.
     * @return The evaluated result of the java.lang.int expression.
     * @throws IOException If an I/O error occurs.
     * @throws FormatException If the input expression is not properly formatted.
     * @throws DivisionByZeroException If the expression contains a division by zero.
     */
    public int ProcessIntInput(String input) throws IOException, FormatException, DivisionByZeroException {
        input = input.substring(1).trim();
        InfixExpressionParser<java.lang.Integer> parser = new InfixExpressionParser(input,java.lang.Integer.class);
        IntEvaluation eval = new IntEvaluation();
        IExpression<java.lang.Integer> expression = parser.parse();
        expression.Accept(eval);
        return eval.GetResult();
    }
    /**
     * Process input string as a Natural expression and returns the evaluated result.
     *
     * @param input The string containing the mathematical expression.
     * @return The evaluated result of the Natural expression.
     * @throws IOException If an I/O error occurs.
     * @throws FormatException If the input expression is not properly formatted.
     * @throws DivisionByZeroException If the expression contains a division by zero.
     */
    public Natural ProcessNaturalInput(String input) throws IOException, FormatException {
        input = input.substring(1).trim();
        InfixExpressionParser<Natural> parser = new InfixExpressionParser<Natural>(input,Natural.class);
        NaturalEvaluation eval = new NaturalEvaluation();

        IExpression<Natural> expression = parser.parse();
        expression.Accept(eval);
        return eval.GetResult();
    }
    /**
     * Process input string as an Integer expression and returns the evaluated result.
     *
     * @param input The string containing the mathematical expression.
     * @return The evaluated result of the Integer expression.
     * @throws IOException If an I/O error occurs.
     * @throws FormatException If the input expression is not properly formatted.
     * @throws DivisionByZeroException If the expression contains a division by zero.
     */
    public Integer ProcessIntegerInput(String input) throws IOException,FormatException{
        input = input.substring(1).trim();
        InfixExpressionParser<Integer> parser = new InfixExpressionParser<Integer>(input,Integer.class);
        IntegerEvaluation eval = new IntegerEvaluation();
        IExpression<Integer> expression = parser.parse();
        expression.Accept(eval);
        return eval.GetResult();
    }
    /**
     * Process input string as a Rational expression and returns the evaluated result.
     *
     * @param input The string containing the mathematical expression.
     * @return The evaluated result of the Rational expression.
     * @throws IOException If an I/O error occurs.
     * @throws FormatException If the input expression is not properly formatted.
     * @throws DivisionByZeroException If the expression contains a division by zero.
     */
    public Rational ProcessRationalInput(String input) throws IOException,FormatException{
        input = input.substring(1).trim();
        InfixExpressionParser<Rational> parser = new InfixExpressionParser<Rational>(input,Rational.class);
        RationalEvaluation eval = new RationalEvaluation();
        IExpression<Rational> expression = parser.parse();
        expression.Accept(eval);

        return eval.GetResult();
    }
    /**
     * Process input string as a Float expression and returns the evaluated result.
     *
     * @param input The string containing the mathematical expression.
     * @return The evaluated result of the Float expression.
     * @throws IOException If an I/O error occurs.
     * @throws FormatException If the input expression is not properly formatted.
     * @throws DivisionByZeroException If the expression contains a division by zero.
     */
    public Float ProcessFloatInput(String input) throws IOException,FormatException{
        input = input.substring(1).trim();
        InfixExpressionParser<Float> parser = new InfixExpressionParser<Float>(input,Float.class);
        FloatEvaluation eval = new FloatEvaluation();
        IExpression<Float> expression = parser.parse();
        expression.Accept(eval);
        return eval.GetResult();
    }
    /**
     * Process input string as a Double expression and returns the evaluated result.
     *
     * @param input The string containing the mathematical expression.
     * @return The evaluated result of the Double expression.
     * @throws IOException If an I/O error occurs.
     * @throws FormatException If the input expression is not properly formatted.
     * @throws DivisionByZeroException If the expression contains a division by zero.
     */
    public Double ProcessDoubleInput(String input) throws IOException,FormatException{
        input = input.substring(1).trim();
        InfixExpressionParser<Double> parser = new InfixExpressionParser<>(input,Double.class);
        DoubleEvaluation eval = new DoubleEvaluation();
        IExpression<Double> expression = parser.parse();
        expression.Accept(eval);
        return eval.GetResult();
    }

    /**
     * Process an input file and evaluates each line based on the specified type.
     *
     * @param inputFile The path to the input file containing mathematical expressions.
     * @param type The type of number expressions (either "int", "natural", "integer", or "rational").
     * @throws IOException If there is an error reading the input file or writing the output file.
     */

    public void ProcessFile(Path inputFile,String type) throws IOException {
        Path outputPath = Paths.get((new File(String.valueOf(inputFile)).getName() + "_output.txt")); // New output file

        List<String> lines = Files.readAllLines(inputFile);
        List<String> processedLines = lines.stream().parallel()
                .map(line -> {
                    try {
                        return processLine(line,type);
                    } catch (Exception e) {
                        return e.getMessage();
                    }
                })
                .toList();
        Files.write(outputPath, processedLines);
        System.out.println("File processing completed. Output written to: " + outputPath);
    }
    /**
     * Process a single line based on the specified type (either "int", "natural", "integer", or "rational").
     *
     * @param line The mathematical expression to be evaluated.
     * @param type The type of number expressions (either "int", "natural", "integer", or "rational").
     * @return A string containing the result of the evaluation.
     * @throws IOException If an I/O error occurs.
     * @throws FormatException If the input expression is not properly formatted.
     * @throws DivisionByZeroException If a division by zero is encountered.
     */
    private String processLine(String line,String type) throws IOException, FormatException, DivisionByZeroException {
        if (type.equals("int")) {
            int result = ProcessIntInput(line);
            return "Result: " + result;
        }else if (type.equals("natural")) {
            Natural result = ProcessNaturalInput(line);
            return "Result: " + result.ToString();
        }else if (type.equals("integer")) {
            Integer result = ProcessIntegerInput(line);
            return "Result: " + result.ToString();
        }else if (type.equals("rational")) {
            Rational num = ProcessRationalInput(line);
            return "Result: " + num.ToString();
        }else if (type.equals("float")) {
            Float result = ProcessFloatInput(line);
            return "Result: " + result.toString();
        }else if (type.equals("double")) {
            Double result = ProcessDoubleInput(line);
            return "Result: " + result.toString();
        }

        return "";
    }

    public void logInfo(String line) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true))) {
            writer.append(line + "\n");
            // You can append more lines as needed
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
