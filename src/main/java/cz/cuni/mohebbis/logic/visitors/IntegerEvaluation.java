package cz.cuni.mohebbis.logic.visitors;

import cz.cuni.mohebbis.logic.exceptions.DivisionByZeroException;
import cz.cuni.mohebbis.logic.expressions.*;
import cz.cuni.mohebbis.logic.interfaces.IExpressionVisiter;
import cz.cuni.mohebbis.logic.numbers.Integer;
/**
 * A visitor that evaluates mathematical expressions for integer numbers.
 * This class processes different types of mathematical expressions and evaluates them
 * as {@code Integer} numbers.
 */
public class IntegerEvaluation implements IExpressionVisiter<Integer> {
    private Integer _result;

    /**
     * A method to return the result of the evaluation.
     * @return {@code Integer} result.
     */
    public Integer GetResult() {
        return _result;
    }
    /**
     * Evaluate a constant expression containing a Integer number.
     * @param exp The constant expression.
     */
    public void Visit(ConstantExpression<Integer> exp) {
        _result =  exp._value;
    }
    /**
     * Evaluates a unary expression for Integer numbers.
     *
     * @param exp The unary expression.
     */
    public void Visit(UnaryExpression<Integer> exp) {
        exp._operand.Accept(this);
        _result = _result.Negate();
    }
    /**
     * Evaluate an addition expression for Integer numbers.
     *
     * @param expr The addition expression.
     */
    public void Visit(PlusExpression<Integer> expr){
        expr.left.Accept(this);
        Integer tempLeft = _result;

        expr.right.Accept(this);
        Integer tempRight = _result;
        _result = tempLeft.Add(tempRight);
    }
    /**
     * Evaluate an subtraction expression for Integer numbers.
     *
     * @param expr The subtraction expression.
     */
    public void Visit(SubtractExpression<Integer> expr){
        expr.left.Accept(this);
        Integer tempLeft = _result;

        expr.right.Accept(this);
        Integer tempRight = _result;

        _result = tempLeft.Subtract(tempRight);
    }
    /**
     * Evaluates a division expression for Integer numbers.
     *
     * @param expr The division expression.
     * @throws DivisionByZeroException If the denominator is zero.
     */
    public void Visit(DivisionExpression<Integer> expr) throws DivisionByZeroException {
        expr.left.Accept(this);
        Integer tempLeft = _result;

        expr.right.Accept(this);
        Integer tempRight = _result;

        if (tempLeft.AbsoluteValue().IsZero()){
            throw new DivisionByZeroException();
        }
        _result = tempLeft.Divide(tempRight);
    }
    /**
     * Evaluate a multiplication expression for Integer numbers.
     *
     * @param expr The multiplication expression.
     */
    public void Visit(MultiplyExpression<Integer> expr){
        expr.left.Accept(this);
        Integer tempLeft = _result;

        expr.right.Accept(this);
        Integer tempRight = _result;

        _result = tempLeft.Multiply(tempRight);
    }
}
