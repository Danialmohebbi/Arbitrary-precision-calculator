package cz.cuni.mohebbis.logic.expressions;

import cz.cuni.mohebbis.logic.interfaces.IExpression;
import cz.cuni.mohebbis.logic.interfaces.IExpressionVisiter;

public class SubtractExpression<T> extends IExpression<T>  {
    public IExpression<T> left;
    public IExpression<T> right;

    public SubtractExpression(IExpression<T> left, IExpression<T> right) {
        this.left = left;
        this.right = right;
    }


    public void Accept(IExpressionVisiter<T> visitor) {
        visitor.Visit(this);
    }


}