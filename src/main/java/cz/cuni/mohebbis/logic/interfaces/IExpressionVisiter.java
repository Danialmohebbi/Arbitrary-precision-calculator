package cz.cuni.mohebbis.logic.interfaces;

import cz.cuni.mohebbis.logic.exceptions.DivisionByZeroException;
import cz.cuni.mohebbis.logic.expressions.*;

/**
 *  Interface for implementing the Visitor pattern design for expressions.
 * This interface defines methods for visiting different types of expressions.
 * @param <T> The type of values used in expressions.
 */
public interface IExpressionVisiter<T> {

    /**
     * Visits a constant expression.
     * @param constant The constant expression to visit.
     */
    void Visit(ConstantExpression<T> constant);

    /**
     * Visits a unary expression.
     * @param unary The unary expression to visit.
     */
    void Visit(UnaryExpression<T> unary);

    /**
     * Visits a plus (addition) expression.
     * @param plus The plus expression to visit.
     */
    void Visit(PlusExpression<T> plus);

    /**
     * Visits a multiplication expression.
     * @param multiply The multiplication expression to visit.
     */
    void Visit(MultiplyExpression<T> multiply);

    /**
     * Visits a division expression.
     * This method may throw a DivisionByZeroException if the divisor is zero.
     * @param division The division expression to visit.
     * @throws DivisionByZeroException If there is an attempt to divide by zero.
     */
    void Visit(DivisionExpression<T> division) throws DivisionByZeroException;

    /**
     * Visits a subtraction expression.
     * @param subtract The subtraction expression to visit.
     */
    void Visit(SubtractExpression<T> subtract);
}
