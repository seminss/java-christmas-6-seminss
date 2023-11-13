package christmas.model.discount.policy;

public enum DiscountConfig {
    DDAY_DISCOUNT("크리스마스 디데이 할인", 1000, 100),
    WEEKDAY_DISCOUNT("평일 할인", 0, 2023),
    WEEKEND_DISCOUNT("주말 할인", 0, 2023),
    SPECIAL_DISCOUNT("특별 할인", 1000, 0);

    private final String description;
    private final int standardAmount;
    private final int increaseAmount;

    DiscountConfig(String description, int standardAmount, int increaseAmount) {
        this.description = description;
        this.standardAmount = standardAmount;
        this.increaseAmount = increaseAmount;
    }

    public String getDescription() {
        return description;
    }

    public int getStandardAmount() {
        return standardAmount;
    }

    public int getIncreaseAmount() {
        return increaseAmount;
    }
}