package cz.cuni.mohebbis.logic;
import cz.cuni.mohebbis.logic.interfaces.IExpressionVisiter;
public abstract class IExpression {
    public abstract void Accept(IExpressionVisiter visitor);
    double _value;
}
