package cz.cuni.mohebbis.logic;

import cz.cuni.mohebbis.logic.expressions.*;
import jdk.jshell.spi.ExecutionControl;

import java.io.IOException;
import java.io.StringReader;

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

    public IExpression parse() throws IOException {
        String token = _tokens[_index++];

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
                throw new ArithmeticException();

        }

    }






}