package cz.cuni.mohebbis.logic.visitors;


import cz.cuni.mohebbis.logic.exceptions.DivisionByZeroException;
import cz.cuni.mohebbis.logic.expressions.*;
import cz.cuni.mohebbis.logic.interfaces.IExpressionVisiter;
import cz.cuni.mohebbis.logic.numbers.Natural;

public class NaturalEvaluation implements IExpressionVisiter<Natural> {
    private Natural _result;
    public Natural GetResult() {
        return _result;
    }
    public void Visit(ConstantExpression<Natural> exp) {
        _result =  exp._value;
    }

    public void Visit(UnaryExpression<Natural> exp) {
        exp._operand.Accept(this);
        _result = _result;
    }

    public void Visit(PlusExpression<Natural> expr){
        expr.left.Accept(this);
        Natural tempLeft = _result;

        expr.right.Accept(this);
        Natural tempRight = _result;
        _result = tempLeft.Add(tempRight);
    }

    public void Visit(SubtractExpression<Natural> expr){
        expr.left.Accept(this);
        Natural tempLeft = _result;

        expr.right.Accept(this);
        Natural tempRight = _result;

        _result = tempLeft.Subtract(tempRight,new boolean[1]);
    }
    public void Visit(DivisionExpression<Natural> expr) throws DivisionByZeroException {
        expr.left.Accept(this);
        Natural tempLeft = _result;

        expr.right.Accept(this);
        Natural tempRight = _result;

        if (tempLeft.IsZero()){
            throw new DivisionByZeroException();
        }
        _result = tempLeft.Divide(tempRight)[0];
    }

    public void Visit(MultiplyExpression<Natural> expr){
        expr.left.Accept(this);
        Natural tempLeft = _result;

        expr.right.Accept(this);
        Natural tempRight = _result;

        _result = tempLeft.Multiply(tempRight);
    }
}
