package cz.cuni.mohebbis.logic.parsers;

import static cz.cuni.mohebbis.logic.utilities.Helper.*;
import cz.cuni.mohebbis.logic.exceptions.FormatException;
import cz.cuni.mohebbis.logic.interfaces.IExpression;

import java.util.Arrays;

public class Lexer {
    private String s;
    private int index;
    private String[] ops = {"*","-","+","\\","~","(",")"};

    public Lexer(String s) {
        this.s = s;
        index = 0;
    }

    public boolean peekNextToken(){
        return index < s.length();
    }

    public String nextToken() {

        while (index < s.length() && s.charAt(index) == ' ') {
            index++;
        }

        if (index >= s.length()) {
            throw new FormatException("Invalid Formula");
        }
        String token = String.valueOf(s.charAt(index));
        index++;

        if (isInteger(token)) {
            String digits = token;
            while (index < s.length() && (isInteger(String.valueOf(s.charAt(index))) || s.charAt(index) == '/')) {
                digits += s.charAt(index++);
            }
            return digits;
        }

        if (!Arrays.stream(ops).toList().contains(token)) {
            throw new FormatException("Invalid Formula");
        }

        return token;



    }
}
