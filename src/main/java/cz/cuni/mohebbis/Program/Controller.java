package cz.cuni.mohebbis.Program;

import cz.cuni.mohebbis.logic.ExpressionParser;
import cz.cuni.mohebbis.logic.IExpression;
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

    public int ProcessIntInput(String input, View view) throws IOException, FormatException, DivisionByZeroException {
        input = input.substring(1).trim();
        ExpressionParser<java.lang.Integer> parser = new ExpressionParser<java.lang.Integer>();
        IntEvaluation eval = new IntEvaluation();
        parser.ParseExpression(input,java.lang.Integer.class);

        //if (!parser.IsDone()){
        //    throw new FormatException("Invalid Formula");
        //}

        IExpression<java.lang.Integer> expression = parser.parse();
        expression.Accept(eval);
        return eval.GetResult();
    }

    public Natural ProcessNaturalInput(String input, View view) throws IOException, FormatException {
        input = input.substring(1).trim();
        ExpressionParser<Natural> parser = new ExpressionParser<Natural>();
        NaturalEvaluation eval = new NaturalEvaluation();
        parser.ParseExpression(input,Natural.class);

        IExpression<Natural> expression = parser.parse();
        expression.Accept(eval);
        return eval.GetResult();
    }

    public Integer ProcessIntegerInput(String input,View view) throws IOException,FormatException{
        input = input.substring(1).trim();
        ExpressionParser<Integer> parser = new ExpressionParser<Integer>();
        IntegerEvaluation eval = new IntegerEvaluation();
        parser.ParseExpression(input,Integer.class);
        IExpression<Integer> expression = parser.parse();
        expression.Accept(eval);
        return eval.GetResult();
    }

    public Rational ProcessRationalInput(String input, View view) throws IOException,FormatException{
        input = input.substring(1).trim();
        ExpressionParser<Rational> parser = new ExpressionParser<Rational>();
        RationalEvaluation eval = new RationalEvaluation();
        parser.ParseExpression(input,Rational.class);
        IExpression<Rational> expression = parser.parse();
        expression.Accept(eval);

        return eval.GetResult();
    }

    public void ProcessFile(Path inputFile, View view,String type) throws IOException {
        Path outputPath = Paths.get((new File(String.valueOf(inputFile)).getName() + "_output.txt")); // New output file

        List<String> lines = Files.readAllLines(inputFile);
        List<String> processedLines = lines.parallelStream()
                .map(line -> {
                    try {
                        return processLine(line,view,type);
                    } catch (Exception e) {
                        return e.getMessage();
                    }
                })
                .toList();
        Files.write(outputPath, processedLines);
        System.out.println("File processing completed. Output written to: " + outputPath);
    }

    private String processLine(String line,View view,String type) throws IOException, FormatException, DivisionByZeroException {
        if (type.equals("int")) {
            int result = ProcessIntInput(line,view);
            return "Result: " + result;
        }else if (type.equals("natural")) {
            Natural result = ProcessNaturalInput(line,view);
            return "Result: " + result.ToString();
        }else if (type.equals("integer")) {
            Integer result = ProcessIntegerInput(line,view);
            return "Result: " + result.ToString();
        }else if (type.equals("rational")) {
            Rational num = ProcessRationalInput(line,view);
            return "Result: " + num.ToString();
        }
        return "";
    }
}
