package christmas.model;

import christmas.config.Badge;
import christmas.model.vo.DiscountAmount;

import static christmas.model.policy.discount.DiscountConfig.GIVEAWAY_ITEM;

public class DiscountResult {
    private final int totalDiscountAMount;
    private final int finalPaymentAmount;
    private final Badge badge;

    public DiscountResult(int initialOrderAmount, DiscountBenefits benefits) {
        this.totalDiscountAMount = calculateTotalAmount(benefits);
        this.finalPaymentAmount = initialOrderAmount + calculateTotalDiscountExcludingGiveaway(benefits);
        this.badge = initializeBadge(finalPaymentAmount);
    }

    public int getTotalDiscountAMount() {
        return totalDiscountAMount;
    }

    public int getFinalPaymentAmount() {
        return finalPaymentAmount;
    }

    public Badge getBadge() {
        return badge;
    }

    private int calculateTotalAmount(DiscountBenefits benefits) {
        return benefits.getDiscounts().stream().mapToInt(DiscountAmount::amount).sum();
    }

    private int calculateTotalDiscountExcludingGiveaway(DiscountBenefits benefits) {
        int totalDiscount = 0;
        for (DiscountAmount discount : benefits.getDiscounts()) {
            if (!discount.discountConfig().equals(GIVEAWAY_ITEM)) {
                totalDiscount += discount.amount();
            }
        }
        return totalDiscount;
    }

    private Badge initializeBadge(int finalPaymentAmount) {
        for (Badge badge : Badge.values()) {
            if (finalPaymentAmount > badge.getThreshold()) {
                return badge;
            }
        }
        return Badge.NONE;
    }
}
