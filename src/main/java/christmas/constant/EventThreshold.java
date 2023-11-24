package christmas.constant;

public enum EventThreshold {
    PROMOTION_THRESHOLD(10_000),
    GIVEAWAY_THRESHOLD(120_000);
    private final int amount;


    EventThreshold(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
