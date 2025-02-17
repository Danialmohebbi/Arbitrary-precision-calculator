package cz.cuni.mohebbis.logic.expressions;

import cz.cuni.mohebbis.logic.IExpression;
import cz.cuni.mohebbis.logic.IExpressionVisiter;

public class ConstantExpression extends IExpression {
    public double _value;
    public ConstantExpression(double value) {
        _value = value;
    }



    public void Accept(IExpressionVisiter visitor) {
        visitor.Visit(this);
    }
}
