package cz.cuni.mohebbis.Program;

import cz.cuni.mohebbis.logic.ExpressionParser;
import cz.cuni.mohebbis.logic.IExpression;
import cz.cuni.mohebbis.logic.expressions.ConstantExpression;
import cz.cuni.mohebbis.logic.expressions.PlusExpression;
import cz.cuni.mohebbis.logic.numbers.Natural;
import cz.cuni.mohebbis.logic.numbers.UInt32;
import cz.cuni.mohebbis.logic.visitors.IntEvaluation;
import cz.cuni.mohebbis.view.View;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        Controller controller = new Controller();
        View view = new View(controller);
        view.processInput();
    }
}
