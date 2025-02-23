package cz.cuni.mohebbis.Program;

import cz.cuni.mohebbis.logic.ExpressionParser;
import cz.cuni.mohebbis.logic.IExpression;
import cz.cuni.mohebbis.logic.exceptions.DivisionByZeroException;
import cz.cuni.mohebbis.logic.exceptions.FormatException;
import cz.cuni.mohebbis.logic.visitors.IntEvaluation;
import cz.cuni.mohebbis.view.View;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;



public class Controller {
    public int ProcessInput(String input, View view) throws IOException, FormatException, DivisionByZeroException {
        input = input.substring(1).trim();
        ExpressionParser parser = new ExpressionParser();
        IntEvaluation eval = new IntEvaluation();
        parser.ParseExpression(input);

        //if (!parser.IsDone()){
        //    throw new FormatException("Invalid Formula");
        //}

        IExpression expression = parser.parse();
        expression.Accept(eval);
        return eval.GetResult();

    }
    public void ProcessFile(Path inputFile, View view,boolean processingFile) throws IOException {
        Path outputPath = Paths.get("output.txt"); // New output file

        List<String> lines = Files.readAllLines(inputFile);
        List<String> processedLines = lines.parallelStream()
                .map(line -> {
                    try {
                        return processLine(line,view);
                    } catch (Exception e) {
                        System.out.println();
                        return e.getMessage();
                    }
                })
                .toList();
        Files.write(outputPath, processedLines);
        System.out.println("File processing completed. Output written to: " + outputPath);
    }

    private String processLine(String line,View view) throws IOException, FormatException, DivisionByZeroException {
        int result = ProcessInput(line,view);
        return "Result: " + result;
    }
}
