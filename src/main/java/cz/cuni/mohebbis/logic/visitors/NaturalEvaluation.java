package cz.cuni.mohebbis.logic.visitors;


import cz.cuni.mohebbis.logic.exceptions.DivisionByZeroException;
import cz.cuni.mohebbis.logic.expressions.*;
import cz.cuni.mohebbis.logic.interfaces.IExpressionVisiter;
import cz.cuni.mohebbis.logic.numbers.Natural;
/**
 * A visitor that evaluates mathematical expressions for natural numbers.
 * This class processes different types of mathematical expressions and evaluates them
 * as {@code Natural} numbers.
 */
public class NaturalEvaluation implements IExpressionVisiter<Natural> {
    private Natural _result;
    /**
     * A method to return the result of the evaluation.
     * @return {@code Natural} result.
     */
    public Natural GetResult() {
        return _result;
    }
    /**
     * Evaluate a constant expression containing a Natural number.
     * @param exp The constant expression.
     */
    public void Visit(ConstantExpression<Natural> exp) {
        _result =  exp._value;
    }
    /**
     * Evaluate a unary expression for natural numbers similar to the constant expression.
     *
     * @param exp The unary expression.
     */
    public void Visit(UnaryExpression<Natural> exp) {
        exp._operand.Accept(this);
        _result = _result;
    }
    /**
     * Evaluate an addition expression for natural numbers.
     *
     * @param expr The addition expression.
     */
    public void Visit(PlusExpression<Natural> expr){
        expr.left.Accept(this);
        Natural tempLeft = _result;

        expr.right.Accept(this);
        Natural tempRight = _result;
        _result = tempLeft.Add(tempRight);
    }
    /**
     * Evaluate an subtraction expression for Natural numbers.
     *
     * @param expr The subtraction expression.
     */
    public void Visit(SubtractExpression<Natural> expr){
        expr.left.Accept(this);
        Natural tempLeft = _result;

        expr.right.Accept(this);
        Natural tempRight = _result;

        _result = tempLeft.Subtract(tempRight,new boolean[1]);
    }
    /**
     * Evaluates a division expression for Natural numbers.
     *
     * @param expr The division expression.
     * @throws DivisionByZeroException If the denominator is zero.
     */
    public void Visit(DivisionExpression<Natural> expr) throws DivisionByZeroException {
        expr.left.Accept(this);
        Natural tempLeft = _result;

        expr.right.Accept(this);
        Natural tempRight = _result;

        if (tempLeft.IsZero()){
            throw new DivisionByZeroException();
        }
        _result = tempLeft.Divide(tempRight)[0];
    }
    /**
     * Evaluate a multiplication expression for natural numbers.
     *
     * @param expr The multiplication expression.
     */
    public void Visit(MultiplyExpression<Natural> expr){
        expr.left.Accept(this);
        Natural tempLeft = _result;

        expr.right.Accept(this);
        Natural tempRight = _result;

        _result = tempLeft.Multiply(tempRight);
    }
}
