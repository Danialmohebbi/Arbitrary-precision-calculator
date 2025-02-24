package cz.cuni.mohebbis.logic.visitors;

import cz.cuni.mohebbis.logic.exceptions.DivisionByZeroException;
import cz.cuni.mohebbis.logic.expressions.*;
import cz.cuni.mohebbis.logic.interfaces.IExpressionVisiter;


public class IntEvaluation implements IExpressionVisiter<Integer> {
    private int _result;

    public int GetResult() {
        return _result;
    }
    public void Visit(ConstantExpression<Integer> exp) {
        _result = (int) exp._value;
    }

    public void Visit(UnaryExpression<Integer> exp) {
        exp._operand.Accept(this);
        _result = -_result;
    }

    public void Visit(PlusExpression<Integer> expr){
        expr.left.Accept(this);
        int tempLeft = _result;

        expr.right.Accept(this);
        int tempRight = _result;
        _result = tempLeft + tempRight;
    }

    public void Visit(SubtractExpression<Integer> expr){
        expr.left.Accept(this);
        int tempLeft = _result;

        expr.right.Accept(this);
        int tempRight = _result;

        _result = tempLeft - tempRight;
    }

    public void Visit(DivisionExpression<Integer> expr) throws DivisionByZeroException {
        expr.left.Accept(this);
        int tempLeft = _result;

        expr.right.Accept(this);
        int tempRight = _result;

        if (tempLeft == 0){
            throw new DivisionByZeroException();
        }
        _result = tempLeft / tempRight;
    }

    public void Visit(MultiplyExpression<Integer> expr){
        expr.left.Accept(this);
        int tempLeft = _result;

        expr.right.Accept(this);
        int tempRight = _result;

        _result = tempLeft * tempRight;
    }





}