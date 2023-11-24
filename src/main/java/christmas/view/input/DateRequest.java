package christmas.view.input;

import christmas.exception.input.EmptyInputException;
import christmas.exception.input.InvalidNumberException;

import static christmas.exception.ValidationErrorMessage.INVALID_DATE;

public class DateRequest {
    private final int visitDate;

    private DateRequest(String userInput) {
        validateNotEmpty(userInput);
        validateFormat(userInput);
        visitDate = parseInteger(userInput);
    }

    public static DateRequest of(String userInput) {
        return new DateRequest(userInput);
    }

    public int getVisitDate() {
        return visitDate;
    }

    private int parseInteger(String userInput) {
        return Integer.parseInt(userInput);
    }

    private void validateNotEmpty(String userInput) {
        if (userInput.isEmpty()) {
            throw new EmptyInputException(INVALID_DATE.getMessage());
        }
    }

    private void validateFormat(String userInput) {
        try {
            Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            throw new InvalidNumberException(INVALID_DATE.getMessage());
        }
    }

}
