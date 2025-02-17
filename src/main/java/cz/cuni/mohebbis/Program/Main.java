package cz.cuni.mohebbis.Program;

import cz.cuni.mohebbis.logic.ExpressionParser;
import cz.cuni.mohebbis.logic.IExpression;
import cz.cuni.mohebbis.logic.expressions.ConstantExpression;
import cz.cuni.mohebbis.logic.expressions.PlusExpression;
import cz.cuni.mohebbis.logic.visitors.IntEvaluation;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            ExpressionParser parser = new ExpressionParser();
            IExpression result = parser.parse();
            IntEvaluation eval = new IntEvaluation();
            result.Accept(eval);
            System.out.println("<!-------");
            System.out.println(eval.GetResult());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
