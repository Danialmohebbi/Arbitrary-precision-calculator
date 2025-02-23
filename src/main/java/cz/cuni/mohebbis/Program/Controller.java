package cz.cuni.mohebbis.Program;

import cz.cuni.mohebbis.logic.ExpressionParser;
import cz.cuni.mohebbis.logic.IExpression;
import cz.cuni.mohebbis.logic.visitors.IntEvaluation;
import cz.cuni.mohebbis.view.View;

import java.io.IOException;

public class Controller {
    public int ProcessInput(String input, View view) throws IOException {
        ExpressionParser parser = new ExpressionParser();
        IntEvaluation eval = new IntEvaluation();
        parser.ParseExpression(input);
        IExpression expression = parser.parse();
        expression.Accept(eval);
        return eval.GetResult();

    }
}
