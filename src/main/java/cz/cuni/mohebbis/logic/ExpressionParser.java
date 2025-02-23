package cz.cuni.mohebbis.logic;

import cz.cuni.mohebbis.logic.exceptions.FormatException;
import cz.cuni.mohebbis.logic.expressions.*;
import jdk.jshell.spi.ExecutionControl;

import java.io.IOException;
import java.io.StringReader;
import java.util.concurrent.ForkJoinPool;

import static cz.cuni.mohebbis.logic.utilities.Int.isInteger;

public class ExpressionParser {
    private String[] _tokens;

    private int _index;


    public void ParseExpression(String expression) {
        _tokens = expression.trim().split("\\s+");
    }

    public boolean IsDone() throws IOException {
        return _index >= _tokens.length;
    }

    public IExpression parse() throws IOException, FormatException {

        if (_index >= _tokens.length) {
            throw new FormatException("Invalid Formula");
        }



        String token = _tokens[_index++];

        if (_index == 1 && !isOperator(token)) {
            throw new FormatException("Invalid Formula: Expression must start with an operator");
        }

        if (isInteger(token)) {
            return new ConstantExpression(Integer.parseInt(token));
        }

        IExpression left, right;

        switch (token) {
            case "+":
                left = parse();
                right = parse();
                return new PlusExpression(left, right);
            case "-":
                left = parse();
                right = parse();
                return new SubtractExpression(left, right);
            case "*":
                left = parse();
                right = parse();
                return new MultiplyExpression(left, right);
            case "/":
                left = parse();
                right = parse();
                return new DivisionExpression(left, right);
            case "~":
                return new UnaryExpression(parse());

            default:
                throw new FormatException("Invalid Formula");

        }


    }
    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/") || token.equals("~");
    }



}