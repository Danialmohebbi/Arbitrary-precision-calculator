package cz.cuni.mohebbis.logic.visitors;

import cz.cuni.mohebbis.logic.exceptions.DivisionByZeroException;
import cz.cuni.mohebbis.logic.expressions.*;
import cz.cuni.mohebbis.logic.interfaces.IExpressionVisiter;
import cz.cuni.mohebbis.logic.numbers.Rational;
/**
 * A visitor that evaluates mathematical expressions for rational numbers.
 * This class processes different types of mathematical expressions and evaluates them
 * as {@code Rational} numbers.
 */
public class RationalEvaluation implements IExpressionVisiter<Rational> {
    private Rational _result;
    /**
     * A method to return the result of the evaluation.
     * @return {@code Rational} result.
     */
    public Rational GetResult() {
        return _result;
    }
    /**
     * Evaluate a constant expression containing a Rational number.
     * @param exp The constant expression.
     */
    public void Visit(ConstantExpression<Rational> exp) {
        _result =  exp._value;
    }
    /**
     * Evaluates a unary expression for rational numbers.
     *
     * @param exp The unary expression.
     */
    public void Visit(UnaryExpression<Rational> exp) {
        exp._operand.Accept(this);
        _result = _result.Negate();
    }

    /**
     * Evaluate an addition expression for rational numbers.
     *
     * @param expr The addition expression.
     */
    public void Visit(PlusExpression<Rational> expr){
        expr.left.Accept(this);
        Rational tempLeft = _result;

        expr.right.Accept(this);
        Rational tempRight = _result;
        _result = tempLeft.Add(tempRight);
    }
    /**
     * Evaluate an subtraction expression for Rational numbers.
     *
     * @param expr The subtraction expression.
     */
    public void Visit(SubtractExpression<Rational> expr){
        expr.left.Accept(this);
        Rational tempLeft = _result;

        expr.right.Accept(this);
        Rational tempRight = _result;

        _result = tempLeft.Subtract(tempRight);
    }
    /**
     * Evaluates a division expression for Rational numbers.
     *
     * @param expr The division expression.
     * @throws DivisionByZeroException If the denominator is zero.
     */
    public void Visit(DivisionExpression<Rational> expr) throws DivisionByZeroException {
        expr.left.Accept(this);
        Rational tempLeft = _result;

        expr.right.Accept(this);
        Rational tempRight = _result;

        if (tempLeft.IsZero()){
            throw new DivisionByZeroException();
        }
        _result = tempLeft.Divide(tempRight);
    }
    /**
     * Evaluate a multiplication expression for rational numbers.
     *
     * @param expr The multiplication expression.
     */
    public void Visit(MultiplyExpression<Rational> expr){
        expr.left.Accept(this);
        Rational tempLeft = _result;

        expr.right.Accept(this);
        Rational tempRight = _result;

        _result = tempLeft.Multiply(tempRight);
    }
}
