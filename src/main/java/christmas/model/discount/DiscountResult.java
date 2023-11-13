package christmas.model.discount;

import java.util.List;

public class DiscountResult {
    private final List<DiscountAmount> discounts;
    private final int totalDiscount;

    public DiscountResult(List<DiscountAmount> discounts) {
        this.discounts = discounts;
        this.totalDiscount = discounts.stream().mapToInt(DiscountAmount::amount).sum();
    }

    public List<DiscountAmount> getDiscounts() {
        return discounts;
    }

    public int getTotalDiscount() {
        return totalDiscount;
    }
}
