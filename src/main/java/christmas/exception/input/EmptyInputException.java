package christmas.exception.input;

public class EmptyInputException extends IllegalArgumentException {
    public EmptyInputException(String message) {
        super(message);
    }
}
