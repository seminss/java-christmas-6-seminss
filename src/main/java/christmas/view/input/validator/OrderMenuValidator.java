package christmas.view.input.validator;

import christmas.exception.input.EmptyInputException;
import christmas.exception.input.InvalidOrderException;

import static christmas.constant.EventSymbol.*;
import static christmas.view.message.ValidationErrorMessage.INPUT_EMPTY;
import static christmas.view.message.ValidationErrorMessage.INVALID_ORDER;

public class OrderMenuValidator {
    public void validate(String userInput) {
        validateNotEmpty(userInput);
        validateFormat(userInput);
    }

    private void validateNotEmpty(String userInput) {
        if (userInput.isEmpty()) {
            throw new EmptyInputException(INPUT_EMPTY.getMessage());
        }
    }

    public void validateFormat(String userInput) {
        String separator = MENU_SEPARATOR.getValue();
        String connector = MENU_CONNECTOR.getValue();
        String regex = "^[가-힣]+"+connector+"\\d+("+separator+"[가-힣]+"+connector+"\\d)*$";
        if (!userInput.matches(regex)) {
            throw new InvalidOrderException(INVALID_ORDER.getMessage());
        }
    }
}
