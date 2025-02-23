package cz.cuni.mohebbis.logic.expressions;


import cz.cuni.mohebbis.logic.IExpression;
import cz.cuni.mohebbis.logic.interfaces.IExpressionVisiter;

public class PlusExpression extends IExpression{
    public IExpression left;
    public IExpression right;
    public PlusExpression(IExpression left, IExpression right) {
        this.left = left;
        this.right = right;
    }

    public void Accept(IExpressionVisiter visitor) {
        visitor.Visit(this);
    }


}
