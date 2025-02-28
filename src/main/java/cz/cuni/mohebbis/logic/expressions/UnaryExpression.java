package cz.cuni.mohebbis.logic.expressions;


import cz.cuni.mohebbis.logic.interfaces.IExpression;
import cz.cuni.mohebbis.logic.interfaces.IExpressionVisiter;

public class UnaryExpression<T> extends IExpression<T> {
    public IExpression<T> _operand;
    public UnaryExpression(IExpression<T> expr) {
        _operand = expr;
    }
    public void Accept(IExpressionVisiter<T> visitor) {
        visitor.Visit(this);
    }



}
