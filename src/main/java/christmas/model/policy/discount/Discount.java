package christmas.model.policy.discount;

import christmas.model.Order;
import christmas.model.VisitDate;
import christmas.model.policy.Promotion;

public abstract class Discount implements Promotion {

    public final String name;

    protected Discount(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int calculateBenefit(Order order, VisitDate visitDate) {
        if (!isDiscountDay(visitDate)) {
            return DEFAULT_DISCOUNT_AMOUNT;
        }
        return calculateDiscountAmount(order, visitDate);
    }

    abstract int calculateDiscountAmount(Order order, VisitDate visitDate);

    abstract boolean isDiscountDay(VisitDate visitDate);
}
