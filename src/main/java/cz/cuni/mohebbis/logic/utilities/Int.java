package cz.cuni.mohebbis.logic.utilities;

public class Int {
    public static boolean isInteger(String s){
        try {
            Integer.parseInt(s);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
