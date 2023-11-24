package christmas.model;

import christmas.exception.business.InvalidOrderException;
import christmas.model.constant.Menu;
import christmas.view.input.OrderRequest;
import christmas.view.input.OrderItem;

import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;

import static christmas.constant.EventThreshold.*;
import static christmas.exception.ValidationErrorMessage.*;

public class Order {
    private final EnumMap<Menu, Integer> orderedMenu;

    private Order(EnumMap<Menu, Integer> orderedMenu) {
        this.orderedMenu = orderedMenu;
    }

    public static Order of(OrderRequest orderRequest) {
        EnumMap<Menu, Integer> orderedMenu = processOrderItems(orderRequest);
        return new Order(orderedMenu);
    }

    public Map<Menu, Integer> getOrderedMenu() {
        return orderedMenu;
    }

    public boolean canReceivePromotion() {
        return getBaseOrderAmount() > PROMOTION_THRESHOLD.getValue();
    }

    public int getMainQuantity() {
        return getQuantityByCategory(Menu.Category.MAIN);
    }

    public int getDessertQuantity() {
        return getQuantityByCategory(Menu.Category.DESSERT);
    }

    public int getBaseOrderAmount() {
        return orderedMenu.keySet().stream()
                .mapToInt(menu -> {
                    int price = menu.getPrice();
                    int quantity = orderedMenu.getOrDefault(menu, 0);
                    return price * quantity;
                })
                .sum();
    }

    private static EnumMap<Menu, Integer> processOrderItems(OrderRequest orderRequest) {
        EnumMap<Menu, Integer> orderedMenu = new EnumMap<>(Menu.class);
        EnumSet<Menu.Category> categories = EnumSet.noneOf(Menu.Category.class);
        for (OrderItem orderItem : orderRequest.getOrderItems()) {
            Menu menu = Menu.of(orderItem.getMenuName());
            Integer quantity = orderItem.getQuantity();
            validateMenuExistence(menu);
            validateMenuDuplication(orderedMenu, menu);
            validateSingleQuantity(quantity);
            categories.add(menu.getCategory());
            orderedMenu.put(menu, quantity);
        }
        validateDrinksOnly(categories);
        validateTotalQuantity(orderedMenu.values());
        return orderedMenu;
    }

    private static void validateDrinksOnly(EnumSet<Menu.Category> categories) {
        if (categories.size() == 1 && categories.contains(Menu.Category.DRINK)) {
            throw new InvalidOrderException(CAN_NOT_DRINK_ONLY.getMessage());
        }
    }

    private static void validateMenuExistence(Menu menu) {
        if (menu == Menu.NONE) {
            throw new InvalidOrderException(INVALID_ORDER.getMessage());
        }
    }

    private static void validateSingleQuantity(Integer quantity) {
        if (quantity < MINIMUM_ORDER_QUANTITY.getValue()) {
            throw new InvalidOrderException(INVALID_ORDER.getMessage());
        }
    }

    private static void validateTotalQuantity(Collection<Integer> values) {
        int totalQuantity = values.stream()
                .mapToInt(Integer::intValue)
                .sum();
        if (totalQuantity > MAXIMUM_ORDER_QUANTITY.getValue()) {
            throw new InvalidOrderException(INVALID_ORDER.getMessage());
        }
    }

    private static void validateMenuDuplication(EnumMap<Menu, Integer> orderedMenu, Menu menu) {
        if (orderedMenu.containsKey(menu)) {
            throw new InvalidOrderException(INVALID_ORDER.getMessage());
        }
    }

    private int getQuantityByCategory(Menu.Category category) {
        return orderedMenu.keySet().stream()
                .filter(menu -> menu.getCategory() == category)
                .mapToInt(orderedMenu::get)
                .sum();
    }

}
