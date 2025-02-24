package cz.cuni.mohebbis.logic;
import cz.cuni.mohebbis.logic.exceptions.DivisionByZeroException;
import cz.cuni.mohebbis.logic.interfaces.IExpressionVisiter;
public abstract class IExpression<T> {
    public abstract void Accept(IExpressionVisiter<T> visitor) throws DivisionByZeroException;
    T _value;
}
