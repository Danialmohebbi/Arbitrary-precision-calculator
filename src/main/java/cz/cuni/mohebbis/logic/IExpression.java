package cz.cuni.mohebbis.logic;
import cz.cuni.mohebbis.logic.exceptions.DivisionByZeroException;
import cz.cuni.mohebbis.logic.interfaces.IExpressionVisiter;
public abstract class IExpression {
    public abstract void Accept(IExpressionVisiter visitor) throws DivisionByZeroException;
    double _value;
}
