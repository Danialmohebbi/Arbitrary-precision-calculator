package cz.cuni.mohebbis.logic.interfaces;


import cz.cuni.mohebbis.logic.exceptions.DivisionByZeroException;
import cz.cuni.mohebbis.logic.expressions.*;

public interface IExpressionVisiter {
    void Visit(ConstantExpression constant);
    void Visit(UnaryExpression unary);
    void Visit(PlusExpression plus);
    void Visit(MultiplyExpression multiply);
    void Visit(DivisionExpression division) throws DivisionByZeroException;
    void Visit(SubtractExpression subtract);
}