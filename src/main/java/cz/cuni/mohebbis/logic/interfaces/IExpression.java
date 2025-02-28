package cz.cuni.mohebbis.logic.interfaces;
import cz.cuni.mohebbis.logic.exceptions.DivisionByZeroException;



/**
 * An abstract base class representing an expression in an expression tree.
 * This class provides a structure for different types of expressions and
 * supports the Visitor pattern for processing expressions.
 * @param <T> The type of values used in expressions.
 */
public abstract class IExpression<T> {

    /** The computed value of the expression */
    protected T _value;

    /**
     * Accepts a visitor that processes this expression.
     * @param visitor The visitor that will process the expression.
     * @throws DivisionByZeroException If a division by zero occurs.
     */
    public abstract void Accept(IExpressionVisiter<T> visitor) throws DivisionByZeroException;
}
