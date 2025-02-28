package cz.cuni.mohebbis.logic.expressions;

import cz.cuni.mohebbis.logic.interfaces.IExpression;
import cz.cuni.mohebbis.logic.interfaces.IExpressionVisiter;

public class ConstantExpression<T> extends IExpression<T> {
    public T _value;
    public ConstantExpression(T value) {
        _value = value;
    }



    public void Accept(IExpressionVisiter<T> visitor) {
        visitor.Visit(this);
    }
}