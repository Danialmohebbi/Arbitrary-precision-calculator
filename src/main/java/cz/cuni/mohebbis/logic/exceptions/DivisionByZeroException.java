package cz.cuni.mohebbis.logic.exceptions;
/**
 * This exception is thrown when an attempt is made to divide by zero,
 * which is an invalid mathematical operation.
 *
 * Recommended usage:
 * - Throw this exception when a division operation encounters a zero denominator.
 * - Use meaningful error messages to help with debugging.
 */
public class DivisionByZeroException extends RuntimeException {
    public DivisionByZeroException(String message) {
        super(message);
    }
    public DivisionByZeroException(String message, Throwable cause) {super(message, cause);}
    public DivisionByZeroException(Throwable cause) {super(cause);}
    public DivisionByZeroException(){super();}
}
