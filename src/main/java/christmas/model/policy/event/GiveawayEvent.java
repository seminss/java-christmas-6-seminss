package christmas.model.policy.event;

import christmas.model.Order;
import christmas.model.VisitDate;

import static christmas.model.constant.Giveaway.CHAMPAGNE_GIVEAWAY;

public class GiveawayEvent extends Event {

    public GiveawayEvent() {
        super("증정 이벤트");
    }

    @Override
    public String getName() {
        return CHAMPAGNE_GIVEAWAY.getMenu().getName();
    }

    @Override
    int calculateDiscountAmount(Order order, VisitDate visitDate) {
        return Math.negateExact(CHAMPAGNE_GIVEAWAY.getThreshold());
    }

    @Override
    boolean canApplyEvent(Order order) {
        return order.getBaseOrderAmount() > CHAMPAGNE_GIVEAWAY.getThreshold();
    }

}