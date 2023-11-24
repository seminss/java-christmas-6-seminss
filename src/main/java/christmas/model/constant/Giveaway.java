package christmas.model.constant;

public enum Giveaway {
    CHAMPAGNE("샴페인", 25_000,1);

    private final String name;
    private final int price;
    private final int quantity;

    Giveaway(String name, int price,int quantity) {
        this.name = name;
        this.price = price;
        this.quantity=quantity;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
