package cz.cuni.mohebbis.Program;

import cz.cuni.mohebbis.logic.ExpressionParser;
import cz.cuni.mohebbis.logic.interfaces.*;
import cz.cuni.mohebbis.logic.exceptions.DivisionByZeroException;
import cz.cuni.mohebbis.logic.exceptions.FormatException;
import cz.cuni.mohebbis.logic.numbers.Natural;
import cz.cuni.mohebbis.logic.numbers.Integer;
import cz.cuni.mohebbis.logic.numbers.Rational;
import cz.cuni.mohebbis.logic.visitors.IntEvaluation;
import cz.cuni.mohebbis.logic.visitors.IntegerEvaluation;
import cz.cuni.mohebbis.logic.visitors.NaturalEvaluation;
import cz.cuni.mohebbis.logic.visitors.RationalEvaluation;
import cz.cuni.mohebbis.view.View;

import java.io.File;
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
        ExpressionParser<java.lang.Integer> parser = new ExpressionParser<java.lang.Integer>();
        IntEvaluation eval = new IntEvaluation();
        parser.ParseExpression(input,java.lang.Integer.class);
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
        ExpressionParser<Natural> parser = new ExpressionParser<Natural>();
        NaturalEvaluation eval = new NaturalEvaluation();
        parser.ParseExpression(input,Natural.class);

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
        ExpressionParser<Integer> parser = new ExpressionParser<Integer>();
        IntegerEvaluation eval = new IntegerEvaluation();
        parser.ParseExpression(input,Integer.class);
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
        ExpressionParser<Rational> parser = new ExpressionParser<Rational>();
        RationalEvaluation eval = new RationalEvaluation();
        parser.ParseExpression(input,Rational.class);
        IExpression<Rational> expression = parser.parse();
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
        }
        return "";
    }
}
