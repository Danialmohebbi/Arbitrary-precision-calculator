package cz.cuni.mohebbis.logic.visitors;

import cz.cuni.mohebbis.logic.exceptions.DivisionByZeroException;
import cz.cuni.mohebbis.logic.expressions.*;
import cz.cuni.mohebbis.logic.interfaces.IExpressionVisiter;

public class FloatEvaluation implements IExpressionVisiter<Float> {
    private Float _result;
    /**
     * A method to return the result of the evaluation.
     * @return {@code java.lang.Float} result.
     */
    public Float GetResult() {
        return _result;
    }
    /**
     * Evaluate a constant expression containing a java.lang.Float number.
     * @param exp The constant expression.
     */
    public void Visit(ConstantExpression<Float> exp) {
        _result = (Float) exp._value;
    }
    /**
     * Evaluates a unary expression for java.lang.Float numbers.
     *
     * @param exp The unary expression.
     */
    public void Visit(UnaryExpression<Float> exp) {
        exp._operand.Accept(this);
        _result = -_result;
    }

    /**
     * Evaluate an addition expression for java.lang.Float numbers.
     *
     * @param expr The addition expression.
     */
    public void Visit(PlusExpression<Float> expr){
        expr.left.Accept(this);
        Float tempLeft = _result;

        expr.right.Accept(this);
        Float tempRight = _result;
        _result = tempLeft + tempRight;
    }
    /**
     * Evaluate an subtraction expression for java.lang.Float numbers.
     *
     * @param expr The subtraction expression.
     */
    public void Visit(SubtractExpression<Float> expr){
        expr.left.Accept(this);
        Float tempLeft = _result;

        expr.right.Accept(this);
        Float tempRight = _result;

        _result = tempLeft - tempRight;
    }
    /**
     * Evaluates a division expression for java.lang.Float numbers.
     *
     * @param expr The division expression.
     * @throws DivisionByZeroException If the denominator is zero.
     */
    public void Visit(DivisionExpression<Float> expr) throws DivisionByZeroException {
        expr.left.Accept(this);
        Float tempLeft = _result;

        expr.right.Accept(this);
        Float tempRight = _result;

        if (tempLeft == 0){
            throw new DivisionByZeroException();
        }
        _result = tempLeft / tempRight;
    }
    /**
     * Evaluate a multiplication expression for java.lang.Float numbers.
     *
     * @param expr The multiplication expression.
     */
    public void Visit(MultiplyExpression<Float> expr){
        expr.left.Accept(this);
        Float tempLeft = _result;

        expr.right.Accept(this);
        Float tempRight = _result;

        _result = tempLeft * tempRight;
    }




}


