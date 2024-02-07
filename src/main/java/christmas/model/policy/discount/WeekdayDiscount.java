package christmas.model.policy.discount;

import christmas.model.Order;
import christmas.model.VisitDate;
import christmas.model.policy.PromotionPeriod;

public class WeekdayDiscount extends Discount {
    private static final int STANDARD = 0;
    private static final int INCREASE = 2_023;

    public WeekdayDiscount() {
        super("평일 할인");
    }

    @Override
    protected int calculateDiscountAmount(Order order, VisitDate visitDate) {
        return Math.negateExact(STANDARD + INCREASE * order.getDessertQuantity());
    }

    @Override
    protected boolean isDiscountDay(VisitDate visitDate) {
        return PromotionPeriod.isWeekday(visitDate.date());
    }

}
