package cz.cuni.mohebbis.logic.visitors;

import cz.cuni.mohebbis.logic.exceptions.DivisionByZeroException;
import cz.cuni.mohebbis.logic.expressions.*;
import cz.cuni.mohebbis.logic.interfaces.IExpressionVisiter;

public class DoubleEvaluation implements IExpressionVisiter<Double> {
    private Double _result;
    /**
     * A method to return the result of the evaluation.
     * @return {@code java.lang.Double} result.
     */
    public Double GetResult() {
        return _result;
    }
    /**
     * Evaluate a constant expression containing a java.lang.Double number.
     * @param exp The constant expression.
     */
    public void Visit(ConstantExpression<Double> exp) {
        _result = (Double) exp._value;
    }
    /**
     * Evaluates a unary expression for java.lang.Double numbers.
     *
     * @param exp The unary expression.
     */
    public void Visit(UnaryExpression<Double> exp) {
        exp._operand.Accept(this);
        _result = -_result;
    }

    /**
     * Evaluate an addition expression for java.lang.Double numbers.
     *
     * @param expr The addition expression.
     */
    public void Visit(PlusExpression<Double> expr){
        expr.left.Accept(this);
        Double tempLeft = _result;

        expr.right.Accept(this);
        Double tempRight = _result;
        _result = tempLeft + tempRight;
    }
    /**
     * Evaluate an subtraction expression for java.lang.Double numbers.
     *
     * @param expr The subtraction expression.
     */
    public void Visit(SubtractExpression<Double> expr){
        expr.left.Accept(this);
        Double tempLeft = _result;

        expr.right.Accept(this);
        Double tempRight = _result;

        _result = tempLeft - tempRight;
    }
    /**
     * Evaluates a division expression for java.lang.Double numbers.
     *
     * @param expr The division expression.
     * @throws DivisionByZeroException If the denominator is zero.
     */
    public void Visit(DivisionExpression<Double> expr) throws DivisionByZeroException {
        expr.left.Accept(this);
        Double tempLeft = _result;

        expr.right.Accept(this);
        Double tempRight = _result;

        if (tempLeft == 0){
            throw new DivisionByZeroException();
        }
        _result = tempLeft / tempRight;
    }
    /**
     * Evaluate a multiplication expression for java.lang.Double numbers.
     *
     * @param expr The multiplication expression.
     */
    public void Visit(MultiplyExpression<Double> expr){
        expr.left.Accept(this);
        Double tempLeft = _result;

        expr.right.Accept(this);
        Double tempRight = _result;

        _result = tempLeft * tempRight;
    }




}
