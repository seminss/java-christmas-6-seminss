package christmas.model;

import christmas.exception.business.InvalidOrderException;

import java.util.AbstractMap.SimpleEntry;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;

import static christmas.exception.ValidationErrorMessage.CAN_NOT_ONLY_DRINK;
import static christmas.exception.ValidationErrorMessage.INVALID_ORDER;

public class Order {
    private final EnumMap<Menu, Integer> order;

    public Order(List<SimpleEntry<String, Integer>> readOrder) {
        validateDrinksOnly(readOrder);
        order = new EnumMap<>(Menu.class);
        for (SimpleEntry<String, Integer> entry : readOrder) {
            Menu menu = Menu.of(entry.getKey());
            Integer quantity = entry.getValue();
            validateMenuExistence(menu);
            validateMenuDuplication(menu);
            validateSingleQuantity(quantity);
            order.put(menu, quantity);
        }
        validateTotalQuantity(order.values());
    }


    private void validateDrinksOnly(List<SimpleEntry<String, Integer>> readOrder) {
        EnumSet<Menu.Category> categories = EnumSet.noneOf(Menu.Category.class);
        for (SimpleEntry<String, Integer> entry : readOrder) {
            Menu menu = Menu.of(entry.getKey());
            categories.add(menu.getCategory());
        }
        if (categories.size() == 1 && categories.contains(Menu.Category.DRINK)) {
            throw new InvalidOrderException(CAN_NOT_ONLY_DRINK.getMessage());
        }
    }


    private void validateMenuExistence(Menu menu) {
        if (menu == Menu.NONE) {
            throw new InvalidOrderException(INVALID_ORDER.getMessage());
        }
    }

    private void validateSingleQuantity(Integer quantity) {
        if (quantity < 1) {
            throw new InvalidOrderException(INVALID_ORDER.getMessage());
        }
    }

    private void validateTotalQuantity(Collection<Integer> values) {
        int totalQuantity = values.stream()
                .mapToInt(Integer::intValue)
                .sum();
        if (totalQuantity > 20) {
            throw new InvalidOrderException(CAN_NOT_ONLY_DRINK.getMessage());
        }
    }

    private void validateMenuDuplication(Menu menu) {
        if (order.containsKey(menu)) {
            throw new InvalidOrderException(INVALID_ORDER.getMessage());
        }
    }

    public EnumMap<Menu, Integer> getOrder() {
        return order;
    }
}
