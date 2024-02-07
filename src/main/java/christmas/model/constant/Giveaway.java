package christmas.model.constant;

public enum Giveaway {
    CHAMPAGNE_GIVEAWAY(Menu.CHAMPAGNE, 1, 120_000);
    private final Menu menu;
    private final int quantity;
    private final int threshold;

    Giveaway(Menu menu, int quantity, int threshold) {
        this.menu = menu;
        this.quantity = quantity;
        this.threshold = threshold;
    }

    public Menu getMenu() {
        return menu;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getThreshold() {
        return threshold;
    }
}
