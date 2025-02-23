package cz.cuni.mohebbis.logic.exceptions;

public class InvalidDigitException extends RuntimeException {
    public InvalidDigitException(String message) {
        super(message);
    }
    public InvalidDigitException(String message, Throwable cause) {super(message, cause);}
    public InvalidDigitException(Throwable cause) {super(cause);}
    public InvalidDigitException(){super();}
}
