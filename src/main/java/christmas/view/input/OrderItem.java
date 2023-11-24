package christmas.view.input;

public class OrderItem {
    private final String menuName;
    private final int quantity;

    private OrderItem(String menuName, int quantity) {
        this.menuName = menuName;
        this.quantity = quantity;
    }

    public static OrderItem of(String menuName, int quantity){
        return new OrderItem(menuName, quantity);
    }

    public String getMenuName() {
        return menuName;
    }

    public int getQuantity() {
        return quantity;
    }
}