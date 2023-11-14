package christmas.model;

import christmas.config.Badge;
import christmas.model.vo.DiscountAmount;

import static christmas.model.policy.discount.DiscountSettings.GIVEAWAY_ITEM;

public class OrderDiscountDetails {
    private final int totalDiscountAmount;
    private final int finalPaymentAmount;
    private final Badge badge;

    public OrderDiscountDetails(int baseOrderValue, DiscountedItems benefits) {
        this.totalDiscountAmount = calculateTotalDiscount(benefits);
        this.finalPaymentAmount = baseOrderValue + calculateTotalDiscountExcludingGiveaway(benefits);
        this.badge = initializeBadge();
    }

    public int getTotalDiscountAmount() {
        return totalDiscountAmount;
    }

    public int getFinalPaymentAmount() {
        return finalPaymentAmount;
    }

    public Badge getBadge() {
        return badge;
    }

    private int calculateTotalDiscount(DiscountedItems benefits) {
        return benefits.discounts().stream().mapToInt(DiscountAmount::amount).sum();
    }

    private int calculateTotalDiscountExcludingGiveaway(DiscountedItems benefits) {
        int totalDiscount = 0;
        for (DiscountAmount discount : benefits.discounts()) {
            if (!discount.discountSettings().equals(GIVEAWAY_ITEM)) {
                totalDiscount += discount.amount();
            }
        }
        return totalDiscount;
    }

    private Badge initializeBadge() {
        for (Badge badge : Badge.values()) {
            if (totalDiscountAmount > badge.getThreshold()) {
                return badge;
            }
        }
        return Badge.NONE;
    }
}
