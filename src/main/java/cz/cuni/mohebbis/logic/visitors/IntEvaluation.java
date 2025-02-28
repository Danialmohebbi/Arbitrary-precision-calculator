package cz.cuni.mohebbis.logic.visitors;

import cz.cuni.mohebbis.logic.exceptions.DivisionByZeroException;
import cz.cuni.mohebbis.logic.expressions.*;
import cz.cuni.mohebbis.logic.interfaces.IExpressionVisiter;

/**
 * A visitor that evaluates mathematical expressions for java.lang.int numbers.
 * This class processes different types of mathematical expressions and evaluates them
 * as {@code java.lang.int} numbers.
 */
public class IntEvaluation implements IExpressionVisiter<Integer> {
    private int _result;
    /**
     * A method to return the result of the evaluation.
     * @return {@code java.lang.int} result.
     */
    public int GetResult() {
        return _result;
    }
    /**
     * Evaluate a constant expression containing a java.lang.int number.
     * @param exp The constant expression.
     */
    public void Visit(ConstantExpression<Integer> exp) {
        _result = (int) exp._value;
    }
    /**
     * Evaluates a unary expression for java.lang.int numbers.
     *
     * @param exp The unary expression.
     */
    public void Visit(UnaryExpression<Integer> exp) {
        exp._operand.Accept(this);
        _result = -_result;
    }

    /**
     * Evaluate an addition expression for java.lang.int numbers.
     *
     * @param expr The addition expression.
     */
    public void Visit(PlusExpression<Integer> expr){
        expr.left.Accept(this);
        int tempLeft = _result;

        expr.right.Accept(this);
        int tempRight = _result;
        _result = tempLeft + tempRight;
    }
    /**
     * Evaluate an subtraction expression for java.lang.int numbers.
     *
     * @param expr The subtraction expression.
     */
    public void Visit(SubtractExpression<Integer> expr){
        expr.left.Accept(this);
        int tempLeft = _result;

        expr.right.Accept(this);
        int tempRight = _result;

        _result = tempLeft - tempRight;
    }
    /**
     * Evaluates a division expression for java.lang.int numbers.
     *
     * @param expr The division expression.
     * @throws DivisionByZeroException If the denominator is zero.
     */
    public void Visit(DivisionExpression<Integer> expr) throws DivisionByZeroException {
        expr.left.Accept(this);
        int tempLeft = _result;

        expr.right.Accept(this);
        int tempRight = _result;

        if (tempLeft == 0){
            throw new DivisionByZeroException();
        }
        _result = tempLeft / tempRight;
    }
    /**
     * Evaluate a multiplication expression for java.lang.int numbers.
     *
     * @param expr The multiplication expression.
     */
    public void Visit(MultiplyExpression<Integer> expr){
        expr.left.Accept(this);
        int tempLeft = _result;

        expr.right.Accept(this);
        int tempRight = _result;

        _result = tempLeft * tempRight;
    }





}