package cz.cuni.mohebbis.logic;

import cz.cuni.mohebbis.logic.exceptions.FormatException;
import cz.cuni.mohebbis.logic.expressions.*;
import cz.cuni.mohebbis.logic.numbers.Natural;
import jdk.jshell.spi.ExecutionControl;

import java.io.IOException;
import java.io.StringReader;
import java.util.concurrent.ForkJoinPool;

import static cz.cuni.mohebbis.logic.utilities.Int.isInteger;

public class ExpressionParser<T> {
    private String[] _tokens;

    private int _index;
    private Class<T> type;

    public void ParseExpression(String expression,Class<T> type) {
        _tokens = expression.trim().split("\\s+");this.type=type;
    }

    public boolean IsDone() throws IOException {
        return _index >= _tokens.length;
    }

    public IExpression<T> parse() throws IOException, FormatException {

        if (_index >= _tokens.length) {
            throw new FormatException("Invalid Formula");
        }



        String token = _tokens[_index++];

        if (_index == 1 && !isOperator(token)) {
            throw new FormatException("Invalid Formula: Expression must start with an operator");
        }


        if (isInteger(token)) {
            if (type == Integer.class) {
                return (IExpression<T>) new ConstantExpression<Integer>(Integer.parseInt(token));
            }else if (type == Natural.class){
                return (IExpression<T>) new ConstantExpression<Natural>(new Natural(Integer.parseInt(token)));
            }else {
                throw new IllegalArgumentException("Unsupported type: " + type);
            }
        }

        IExpression<T> left, right;

        switch (token) {
            case "+":
                left = parse();
                right = parse();
                return new PlusExpression<T>(left, right);
            case "-":
                left = parse();
                right = parse();
                return new SubtractExpression<T>(left, right);
            case "*":
                left = parse();
                right = parse();
                return new MultiplyExpression<T>(left, right);
            case "/":
                left = parse();
                right = parse();
                return new DivisionExpression<T>(left, right);
            case "~":
                return new UnaryExpression<T>(parse());

            default:
                throw new FormatException("Invalid Formula");

        }


    }
    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/") || token.equals("~");
    }



}