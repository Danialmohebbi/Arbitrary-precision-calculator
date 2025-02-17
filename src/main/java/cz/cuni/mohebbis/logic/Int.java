package cz.cuni.mohebbis.logic;

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
