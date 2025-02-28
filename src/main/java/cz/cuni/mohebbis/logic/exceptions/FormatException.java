package cz.cuni.mohebbis.logic.exceptions;
/**
 * This exception is thrown when a given formula is invalid.
 */
public class FormatException extends RuntimeException {
    public FormatException(String message) {
        super(message);
    }
    public FormatException(String message, Throwable cause) {super(message, cause);}
    public FormatException(Throwable cause) {super(cause);}
    public FormatException(){super();}
}
