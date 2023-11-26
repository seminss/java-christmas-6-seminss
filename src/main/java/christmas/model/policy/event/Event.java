package christmas.model.policy.event;

import christmas.model.Order;
import christmas.model.VisitDate;
import christmas.model.policy.Promotion;


public abstract class Event implements Promotion {
    public final String name;

    protected Event(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int calculateBenefit(Order order, VisitDate visitDate){
        if (!canApplyEvent(order)) {
            return DEFAULT_DISCOUNT_AMOUNT;
        }
        return calculateDiscountAmount(order, visitDate);
    }

    abstract int calculateDiscountAmount(Order order, VisitDate visitDate);

    abstract boolean canApplyEvent(Order order);
}
