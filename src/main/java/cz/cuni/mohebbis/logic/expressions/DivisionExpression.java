package cz.cuni.mohebbis.logic.expressions;


import cz.cuni.mohebbis.logic.IExpression;
import cz.cuni.mohebbis.logic.exceptions.DivisionByZeroException;
import cz.cuni.mohebbis.logic.interfaces.IExpressionVisiter;

public class DivisionExpression<T> extends IExpression<T>{
    public IExpression<T> left;
    public IExpression<T> right;


    public DivisionExpression(IExpression<T> left, IExpression<T> right) {
        this.left = left;
        this.right = right;
    }
    public void Accept(IExpressionVisiter<T> visitor) throws DivisionByZeroException {
        visitor.Visit(this);
    }

}