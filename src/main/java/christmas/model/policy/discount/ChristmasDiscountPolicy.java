package christmas.model.policy.discount;

import static christmas.model.policy.discount.DiscountSettings.*;

public class ChristmasDiscountPolicy implements DiscountPolicy {
    static final int EVENT_START_DAY = 1;

    @Override
    public int calculateGiveawayItem() {
        return Math.negateExact(GIVEAWAY_DISCOUNT.getStandardAmount());
    }

    @Override
    public int calculateDDayDiscountPrice(int visitDay) {
        return Math.negateExact(DDAY_DISCOUNT.getStandardAmount()
                + (visitDay - EVENT_START_DAY) * DDAY_DISCOUNT.getIncreaseAmount());
    }

    @Override
    public int calculateWeekdayDiscountPrice(int dessertQuantity) {
        return Math.negateExact(WEEKDAY_DISCOUNT.getIncreaseAmount() * dessertQuantity);
    }

    @Override
    public int calculateWeekendDiscountPrice(int mainQuantity) {
        return Math.negateExact(WEEKEND_DISCOUNT.getIncreaseAmount() * mainQuantity);
    }

    @Override
    public int calculateSpecialDiscountPrice() {
        return Math.negateExact(SPECIAL_DISCOUNT.getStandardAmount());
    }
}
