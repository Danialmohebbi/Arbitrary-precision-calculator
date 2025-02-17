package cz.cuni.mohebbis.logic.expressions;


import cz.cuni.mohebbis.logic.IExpression;
import cz.cuni.mohebbis.logic.interfaces.IExpressionVisiter;

public class UnaryExpression extends IExpression {
    public IExpression _operand;
    public UnaryExpression(IExpression expr) {
        _operand = expr;
    }
    public void Accept(IExpressionVisiter visitor) {
        visitor.Visit(this);
    }



}
