package christmas.model;

import christmas.model.vo.DiscountAmount;

import java.util.List;

public class DiscountBenefits {
    private final List<DiscountAmount> discounts;

    public DiscountBenefits(List<DiscountAmount> discounts) {
        this.discounts = discounts;
    }

    public List<DiscountAmount> getDiscounts() {
        return discounts;
    }
}
