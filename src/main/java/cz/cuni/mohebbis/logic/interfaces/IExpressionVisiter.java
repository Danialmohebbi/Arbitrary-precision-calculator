package cz.cuni.mohebbis.logic.interfaces;


import cz.cuni.mohebbis.logic.exceptions.DivisionByZeroException;
import cz.cuni.mohebbis.logic.expressions.*;

public interface IExpressionVisiter<T> {
    void Visit(ConstantExpression<T> constant);
    void Visit(UnaryExpression<T> unary);
    void Visit(PlusExpression<T> plus);
    void Visit(MultiplyExpression<T> multiply);
    void Visit(DivisionExpression<T> division) throws DivisionByZeroException;
    void Visit(SubtractExpression<T> subtract);
}