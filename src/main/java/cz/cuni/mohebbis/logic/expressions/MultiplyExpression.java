package cz.cuni.mohebbis.logic.expressions;

import cz.cuni.mohebbis.logic.IExpression;
import cz.cuni.mohebbis.logic.IExpressionVisiter;

public class MultiplyExpression extends IExpression{
    public IExpression left;
    public IExpression right;
    double _value;

    public MultiplyExpression(IExpression left, IExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void Accept(IExpressionVisiter visitor) {
        visitor.Visit(this);
    }


}
