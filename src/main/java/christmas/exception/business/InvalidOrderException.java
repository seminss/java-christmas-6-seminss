package christmas.exception.business;

public class InvalidOrderException extends IllegalArgumentException {
    public InvalidOrderException(String message) {
        super(message);
    }
}