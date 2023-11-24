package christmas.view.input.validator;

import christmas.exception.input.EmptyInputException;
import christmas.exception.input.InvalidNumberException;

import static christmas.exception.ValidationErrorMessage.*;

public class VisitDateValidator {

    public static void validate(String userInput) {
        validateNotEmpty(userInput);
        validateFormat(userInput);
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
