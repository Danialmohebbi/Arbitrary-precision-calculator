package cz.cuni.mohebbis.logic.visitors;

import cz.cuni.mohebbis.logic.exceptions.DivisionByZeroException;
import cz.cuni.mohebbis.logic.expressions.*;
import cz.cuni.mohebbis.logic.interfaces.IExpressionVisiter;


public class IntEvaluation implements IExpressionVisiter {
    private int _result;

    public int GetResult() {
        return _result;
    }
    public void Visit(ConstantExpression exp) {
        _result = (int) exp._value;
    }

    public void Visit(UnaryExpression exp) {
        exp._operand.Accept(this);
        _result = -_result;
    }

    public void Visit(PlusExpression expr){
        expr.left.Accept(this);
        int tempLeft = _result;

        expr.right.Accept(this);
        int tempRight = _result;
        System.out.println(tempLeft + " " + tempRight);
        _result = tempLeft + tempRight;
    }

    public void Visit(SubtractExpression expr){
        expr.left.Accept(this);
        int tempLeft = _result;

        expr.right.Accept(this);
        int tempRight = _result;

        _result = tempLeft - tempRight;
    }

    public void Visit(DivisionExpression expr){
        expr.left.Accept(this);
        int tempLeft = _result;

        expr.right.Accept(this);
        int tempRight = _result;

        if (tempLeft == 0){
            throw new DivisionByZeroException();
        }
        _result = tempLeft / tempRight;
    }

    public void Visit(MultiplyExpression expr){
        expr.left.Accept(this);
        int tempLeft = _result;

        expr.right.Accept(this);
        int tempRight = _result;

        _result = tempLeft * tempRight;
    }





}
