package cz.cuni.mohebbis.logic.visitors;

import cz.cuni.mohebbis.logic.exceptions.DivisionByZeroException;
import cz.cuni.mohebbis.logic.expressions.*;
import cz.cuni.mohebbis.logic.interfaces.IExpressionVisiter;
import cz.cuni.mohebbis.logic.numbers.Integer;

public class IntegerEvaluation implements IExpressionVisiter<Integer> {
    private Integer _result;
    public Integer GetResult() {
        return _result;
    }
    public void Visit(ConstantExpression<Integer> exp) {
        _result =  exp._value;
    }

    public void Visit(UnaryExpression<Integer> exp) {
        exp._operand.Accept(this);
        _result = _result.Negate();
    }

    public void Visit(PlusExpression<Integer> expr){
        expr.left.Accept(this);
        Integer tempLeft = _result;

        expr.right.Accept(this);
        Integer tempRight = _result;
        _result = tempLeft.Add(tempRight);
    }

    public void Visit(SubtractExpression<Integer> expr){
        expr.left.Accept(this);
        Integer tempLeft = _result;

        expr.right.Accept(this);
        Integer tempRight = _result;

        _result = tempLeft.Subtract(tempRight);
    }
    public void Visit(DivisionExpression<Integer> expr) throws DivisionByZeroException {
        expr.left.Accept(this);
        Integer tempLeft = _result;

        expr.right.Accept(this);
        Integer tempRight = _result;

        if (tempLeft.AbsoluteValue().IsZero()){
            throw new DivisionByZeroException();
        }
        _result = tempLeft.Divide(tempRight);
    }

    public void Visit(MultiplyExpression<Integer> expr){
        expr.left.Accept(this);
        Integer tempLeft = _result;

        expr.right.Accept(this);
        Integer tempRight = _result;

        _result = tempLeft.Multiply(tempRight);
    }
}
