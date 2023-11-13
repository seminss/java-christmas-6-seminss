package christmas.model;

import christmas.config.Badge;

public class DiscountResult {
    private final int finalPaymentAmount;
    private final Badge badge;

    public DiscountResult(int initialOrderAmount, int totalDiscount) {
        this.finalPaymentAmount = initialOrderAmount - totalDiscount;
        this.badge = initializeBadge(finalPaymentAmount);
    }

    private Badge initializeBadge(int finalPaymentAmount) {
        for (Badge badge : Badge.values()) {
            if (finalPaymentAmount > badge.getThreshold()) {
                return this.badge;
            }
        }
        return Badge.NONE;
    }

    public int getFinalPaymentAmount() {
        return finalPaymentAmount;
    }

    public Badge getBadge() {
        return badge;
    }
}
