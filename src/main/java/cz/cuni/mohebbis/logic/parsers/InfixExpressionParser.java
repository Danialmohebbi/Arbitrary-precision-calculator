package cz.cuni.mohebbis.logic.parsers;

import cz.cuni.mohebbis.logic.exceptions.FormatException;
import cz.cuni.mohebbis.logic.expressions.*;
import cz.cuni.mohebbis.logic.interfaces.IExpression;
import cz.cuni.mohebbis.logic.numbers.Natural;
import cz.cuni.mohebbis.logic.numbers.Rational;

import static cz.cuni.mohebbis.logic.utilities.Helper.*;

public class InfixExpressionParser<T> {
    Class<T> type;
    Lexer lexer;

    public InfixExpressionParser(String s, Class<T> type) {
        lexer = new Lexer(s);
        this.type = type;
    }

    public IExpression<T> parse() {
        String token = lexer.nextToken();

        if (token.equals("~")) {
            IExpression<T> next = parse();
            return new UnaryExpression<T>(next);
        }

        if (type == Rational.class && token.contains("/")) {
            String[] input = token.split("/");
            Rational r = new Rational(new cz.cuni.mohebbis.logic.numbers.Integer(Integer.parseInt(input[0])),
                    new cz.cuni.mohebbis.logic.numbers.Integer(Integer.parseInt(input[1])));
            return (IExpression<T>) new ConstantExpression<Rational>(r);
        }
        if (type == Double.class && isDouble(token)) {
            return (IExpression<T>) new ConstantExpression<Double>(Double.parseDouble(token));
        }
        if (type == Float.class && isFloat(token)) {
            return (IExpression<T>) new ConstantExpression<Float>(Float.parseFloat(token));
        }
        if (isInteger(token)) {
            if (type == Integer.class) {
                return (IExpression<T>) new ConstantExpression<Integer>(Integer.parseInt(token));
            } else if (type == Natural.class) {
                return (IExpression<T>) new ConstantExpression<Natural>(new Natural(Integer.parseInt(token)));
            } else if (type == cz.cuni.mohebbis.logic.numbers.Integer.class) {
                cz.cuni.mohebbis.logic.numbers.Integer a = new cz.cuni.mohebbis.logic.numbers.Integer(false, new Natural(Integer.parseInt(token)));
                return (IExpression<T>) new ConstantExpression<cz.cuni.mohebbis.logic.numbers.Integer>(a);
            } else {
                throw new IllegalArgumentException("Unsupported type: " + type);
            }
        }

        if (token.equals("(")) {
            IExpression<T> left = parse();

            String op = lexer.nextToken();
            if (op.equals("~")) {
                IExpression<T> next = parse();
                return new UnaryExpression<T>(next);
            }

            int i = 0;
            if (op.equals("+")) {
                i = 1;
            } else if (op.equals("-")) {
                i = 2;
            } else if (op.equals("*")) {
                i = 3;
            } else if (op.equals("\\")) {
                i = 4;
            } else {
                throw new FormatException("Invalid operator: " + op);
            }

            IExpression<T> right = parse();
            String w = lexer.nextToken();
            if (!w.equals(")")) {
                throw new FormatException("Expected ')' but found: " + w);
            }

            switch (i) {
                case 1:
                    return new PlusExpression<T>(left, right);
                case 2:
                    return new SubtractExpression<T>(left, right);
                case 3:
                    return new MultiplyExpression<T>(left, right);
                case 4:
                    return new DivisionExpression<T>(left, right);
                default:
                    throw new FormatException("Invalid operator code: " + i);
            }
        } else {
            throw new FormatException("Invalid token: " + token);
        }
    }
}