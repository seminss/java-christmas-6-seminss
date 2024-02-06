package christmas.model.policy.discount;

import christmas.model.Order;
import christmas.model.VisitDate;
import christmas.model.policy.PromotionPeriod;

public class DdayDiscount extends Discount {
    private static final int STANDARD = 1_000;
    private static final int INCREASE = 100;

    public DdayDiscount() {
        super("크리스마스 디데이 할인");
    }

    @Override
    protected int calculateDiscountAmount(Order order, VisitDate visitDate) {
        return Math.negateExact(
                STANDARD + (visitDate.date().getDayOfMonth() - PromotionPeriod.getStartDay()) * INCREASE);
    }

    @Override
    protected boolean isDiscountDay(VisitDate visitDate) {
        return visitDate.date().getDayOfMonth() <= PromotionPeriod.getDday();
    }

}
