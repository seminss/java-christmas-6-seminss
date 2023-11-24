package christmas.model;

import christmas.model.constant.Badge;
import christmas.model.vo.DiscountAmount;

import static christmas.model.policy.discount.DiscountSettings.GIVEAWAY_DISCOUNT;

public class DiscountResults {
    private final int totalDiscountAmount;
    private final int finalPaymentAmount;
    private final Badge badge;

    public DiscountResults(int baseOrderAmount, DiscountedItems discountedItems) {
        this.totalDiscountAmount = calculateTotalDiscount(discountedItems);
        this.finalPaymentAmount = baseOrderAmount + calculateTotalDiscountExcludingGiveaway(discountedItems);
        this.badge = Badge.of(totalDiscountAmount);
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
        return benefits.items().stream().mapToInt(DiscountAmount::amount).sum();
    }

    private int calculateTotalDiscountExcludingGiveaway(DiscountedItems benefits) {
        int totalDiscount = 0;
        for (DiscountAmount discount : benefits.items()) {
            if (!discount.discountSettings().equals(GIVEAWAY_DISCOUNT)) {
                totalDiscount += discount.amount();
            }
        }
        return totalDiscount;
    }
}
