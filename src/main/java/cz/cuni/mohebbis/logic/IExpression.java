package cz.cuni.mohebbis.logic;

public abstract class IExpression {
    public abstract void Accept(IExpressionVisiter visitor);
    double _value;
}
