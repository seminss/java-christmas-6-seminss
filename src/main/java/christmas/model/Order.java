package christmas.model;

import christmas.exception.business.InvalidOrderException;
import christmas.config.Menu;

import java.util.AbstractMap.SimpleEntry;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;

import static christmas.exception.ValidationErrorMessage.CAN_NOT_DRINK_ONLY;
import static christmas.exception.ValidationErrorMessage.INVALID_ORDER;

public class Order {
    private final EnumMap<Menu, Integer> orderedMenu;

    public Order(List<SimpleEntry<String, Integer>> readOrder) {
        validateDrinksOnly(readOrder);
        orderedMenu = new EnumMap<>(Menu.class);
        for (SimpleEntry<String, Integer> entry : readOrder) {
            Menu menu = Menu.of(entry.getKey());
            Integer quantity = entry.getValue();
            validateMenuExistence(menu);
            validateMenuDuplication(menu);
            validateSingleQuantity(quantity);
            orderedMenu.put(menu, quantity);
        }
        validateTotalQuantity(orderedMenu.values());
    }

    public EnumMap<Menu, Integer> getOrderedMenu() {
        return orderedMenu;
    }


    public int getMainQuantity() {
        int mainQuantity = 0;
        for (Menu menu : orderedMenu.keySet()) {
            if (menu.getCategory() == Menu.Category.MAIN) {
                mainQuantity += orderedMenu.get(menu);
            }
        }
        return mainQuantity;
    }

    public int getDessertQuantity() {
        int dessertQuantity = 0;
        for (Menu menu : orderedMenu.keySet()) {
            if (menu.getCategory() == Menu.Category.DESSERT) {
                dessertQuantity += orderedMenu.get(menu);
            }
        }
        return dessertQuantity;
    }

    public int calculateTotalOrderAmount() {
        int amount = 0;
        for (Menu menu : Menu.values()) {
            int quantity = orderedMenu.getOrDefault(menu, 0);
            amount += quantity * menu.getPrice();
        }
        return amount;
    }


    private void validateDrinksOnly(List<SimpleEntry<String, Integer>> readOrder) {
        EnumSet<Menu.Category> categories = EnumSet.noneOf(Menu.Category.class);
        for (SimpleEntry<String, Integer> entry : readOrder) {
            Menu menu = Menu.of(entry.getKey());
            categories.add(menu.getCategory());
        }
        if (categories.size() == 1 && categories.contains(Menu.Category.DRINK)) {
            throw new InvalidOrderException(CAN_NOT_DRINK_ONLY.getMessage());
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
            throw new InvalidOrderException(INVALID_ORDER.getMessage());
        }
    }

    private void validateMenuDuplication(Menu menu) {
        if (orderedMenu.containsKey(menu)) {
            throw new InvalidOrderException(INVALID_ORDER.getMessage());
        }
    }
}
