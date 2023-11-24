package christmas.constant;

public enum EventThreshold {
    MINIMUM_ORDER_QUANTITY(1),
    MAXIMUM_ORDER_QUANTITY(20),
    PROMOTION_THRESHOLD(10_000),
    GIVEAWAY_THRESHOLD(120_000);
    private final int value;


    EventThreshold(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
