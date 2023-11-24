package christmas.view.input;

import christmas.exception.input.EmptyInputException;
import christmas.exception.input.InvalidNumberException;

import static christmas.exception.ValidationErrorMessage.INVALID_DATE;

public class DateRequest {
    private final int visitDate;

    private DateRequest(String userInput){
        visitDate = parseInteger(userInput);
    }

    public static DateRequest valueOf(String userInput){
        validateNotEmpty(userInput);
        validateFormat(userInput);
        return new DateRequest(userInput);
    }

    private int parseInteger(String userInput) {
        return Integer.parseInt(userInput);
    }

    private static void validateNotEmpty(String userInput) {
        if (userInput.isEmpty()) {
            throw new EmptyInputException(INVALID_DATE.getMessage());
        }
    }

    private static void validateFormat(String userInput) {
        try {
            Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            throw new InvalidNumberException(INVALID_DATE.getMessage());
        }
    }

}
