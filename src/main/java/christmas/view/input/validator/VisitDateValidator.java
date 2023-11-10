package christmas.view.input.validator;

import christmas.exception.input.EmptyInputException;
import christmas.exception.input.InvalidNumberException;

import static christmas.exception.ValidationErrorMessage.INPUT_EMPTY;
import static christmas.exception.ValidationErrorMessage.INPUT_NOT_INTEGER;

public class VisitDateValidator {

    public static void validate(String userInput) {
        validateNotEmpty(userInput);
        validateFormat(userInput);
    }

    private static void validateNotEmpty(String userInput) {
        if (userInput.isEmpty()) {
            throw new EmptyInputException(INPUT_EMPTY.getMessage());
        }
    }

    private static void validateFormat(String userInput) {
        try {
            Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            throw new InvalidNumberException(INPUT_NOT_INTEGER.getMessage());
        }
    }
}
