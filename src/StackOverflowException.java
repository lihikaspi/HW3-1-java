/**
 * This class represents an unchecked stack overflow exception
 * Inherits StackException
 */
public class StackOverflowException extends StackException {

    public StackOverflowException() {

    }

    public StackOverflowException(String message) {
        super(message);
    }

    public StackOverflowException(String message, Throwable cause) {
        super(message, cause);
    }
}
