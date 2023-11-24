package christmas.view.input;

import christmas.exception.input.EmptyInputException;
import christmas.exception.input.InvalidOrderException;

import java.util.Arrays;
import java.util.List;

import static christmas.constant.EventSymbol.MENU_CONNECTOR;
import static christmas.constant.EventSymbol.MENU_SEPARATOR;
import static christmas.exception.ValidationErrorMessage.INVALID_ORDER;

public class OrderRequest {
    private final List<OrderItem> orderItems;

    private OrderRequest(String userInput) {
        validateNotEmpty(userInput);
        validateFormat(userInput);
        this.orderItems = parseEachMenu(userInput);
    }

    public static OrderRequest of(String userInput) {
        return new OrderRequest(userInput);
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    private List<OrderItem> parseEachMenu(String userInput) {
        return Arrays.stream(userInput.split(MENU_SEPARATOR.getValue()))
                .map(item -> {
                    String[] parts = item.trim().split(MENU_CONNECTOR.getValue());
                    return OrderItem.of(parts[0], Integer.parseInt(parts[1]));
                })
                .toList();
    }

    private void validateNotEmpty(String userInput) {
        if (userInput.isEmpty()) {
            throw new EmptyInputException(INVALID_ORDER.getMessage());
        }
    }

    private void validateFormat(String userInput) {
        String separator = MENU_SEPARATOR.getValue();
        String connector = MENU_CONNECTOR.getValue();
        String regex = "^[가-힣]+"+ connector +"\\d+("+ separator +"[가-힣]+"+ connector +"\\d+)*$";
        if (!userInput.matches(regex)) {
            throw new InvalidOrderException(INVALID_ORDER.getMessage());
        }
    }
}
