package christmas.exception.business;

public class InvalidDateException extends IllegalArgumentException {
    public InvalidDateException(String message) {
        super(message);
    }
}
