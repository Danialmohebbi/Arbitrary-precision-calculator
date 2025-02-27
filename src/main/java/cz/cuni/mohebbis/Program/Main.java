package cz.cuni.mohebbis.Program;

import cz.cuni.mohebbis.logic.numbers.Integer;
import cz.cuni.mohebbis.logic.numbers.Natural;
import cz.cuni.mohebbis.view.View;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Main {
    static void change(boolean[] a){
        a[0] = true;
    }
    public static void main(String[] args) throws IOException {
        Controller controller = new Controller();
        View view = new View(controller);
        view.processInput();
    }
}
