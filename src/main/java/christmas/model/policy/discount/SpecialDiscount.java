package christmas.model.policy.discount;

import christmas.model.Order;
import christmas.model.VisitDate;
import christmas.model.policy.PromotionPeriod;

public class SpecialDiscount extends Discount {
    private static final int STANDARD = 1_000;

    public SpecialDiscount() {
        super("특별 할인");
    }

    @Override
    protected int calculateDiscountAmount(Order order, VisitDate visitDate) {
        return Math.negateExact(STANDARD);
    }

    @Override
    protected boolean isDiscountDay(VisitDate visitDate) {
        return PromotionPeriod.getSpecialDays().contains(visitDate.date().getDayOfMonth());
    }

}
