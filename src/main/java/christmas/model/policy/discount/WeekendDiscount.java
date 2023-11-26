package christmas.model.policy.discount;

import christmas.model.Order;
import christmas.model.VisitDate;
import christmas.model.policy.PromotionPeriod;

public class WeekendDiscount extends Discount {
    private static final int STANDARD = 0;
    private static final int INCREASE = 2023;

    public WeekendDiscount() {
        super("주말 할인");
    }

    @Override
    protected int calculateDiscountAmount(Order order, VisitDate visitDate) {
        return Math.negateExact(STANDARD + INCREASE * order.getDessertQuantity());
    }

    @Override
    protected boolean isDiscountDay(VisitDate visitDate) {
        return !PromotionPeriod.isWeekday(visitDate.date());
    }

}
