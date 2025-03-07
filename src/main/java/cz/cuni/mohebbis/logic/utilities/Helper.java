package cz.cuni.mohebbis.logic.utilities;

/**
 * Helper Utility Int class
 */
public class Helper {
    /**
     * Checks whether a String can be converted to an int.
     * @param s the String to be checked.
     * @return {@code true} if the {@String s} can be converted to an {@int}, otherwise {@code false}.
     */
    public static boolean isInteger(String s){
        try {
            Integer.parseInt(s);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    public static boolean isDouble(String s){
        try {
            Double.parseDouble(s);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    public static boolean isFloat(String s){
        try {
            Float.parseFloat(s);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
