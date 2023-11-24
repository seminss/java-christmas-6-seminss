package christmas.exception.input;

public class InvalidOrderException extends IllegalArgumentException {
    public InvalidOrderException(String message) {
        super(message);
    }
}
