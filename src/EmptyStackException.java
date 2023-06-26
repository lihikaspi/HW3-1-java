/**
 * This class represents an unchecked empty stack exception
 * Inherits from StackException
 */
public class EmptyStackException extends StackException {

    public EmptyStackException() {

    }

    public EmptyStackException(String message) {
        super(message);
    }

    public EmptyStackException(String message, Throwable cause) {
        super(message, cause);
    }
}
