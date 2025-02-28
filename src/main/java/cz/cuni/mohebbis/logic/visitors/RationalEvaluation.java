package cz.cuni.mohebbis.logic.visitors;

import cz.cuni.mohebbis.logic.exceptions.DivisionByZeroException;
import cz.cuni.mohebbis.logic.expressions.*;
import cz.cuni.mohebbis.logic.interfaces.IExpressionVisiter;
import cz.cuni.mohebbis.logic.numbers.Rational;

public class RationalEvaluation implements IExpressionVisiter<Rational> {
    private Rational _result;
    public Rational GetResult() {
        return _result;
    }
    public void Visit(ConstantExpression<Rational> exp) {
        _result =  exp._value;
    }

    public void Visit(UnaryExpression<Rational> exp) {
        exp._operand.Accept(this);
        _result = _result.Negate();
    }


    public void Visit(PlusExpression<Rational> expr){
        expr.left.Accept(this);
        Rational tempLeft = _result;

        expr.right.Accept(this);
        Rational tempRight = _result;
        _result = tempLeft.Add(tempRight);
    }

    public void Visit(SubtractExpression<Rational> expr){
        expr.left.Accept(this);
        Rational tempLeft = _result;

        expr.right.Accept(this);
        Rational tempRight = _result;

        _result = tempLeft.Subtract(tempRight);
    }

    public void Visit(DivisionExpression<Rational> expr) throws DivisionByZeroException {
        expr.left.Accept(this);
        Rational tempLeft = _result;

        expr.right.Accept(this);
        Rational tempRight = _result;

        if (tempLeft.IsZero()){
            throw new DivisionByZeroException();
        }
        _result = tempLeft.Divide(tempRight);
    }

    public void Visit(MultiplyExpression<Rational> expr){
        expr.left.Accept(this);
        Rational tempLeft = _result;

        expr.right.Accept(this);
        Rational tempRight = _result;

        _result = tempLeft.Multiply(tempRight);
    }
}
