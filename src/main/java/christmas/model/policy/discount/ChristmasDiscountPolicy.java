package christmas.model.policy.discount;

import static christmas.model.policy.discount.DiscountSettings.*;

public class ChristmasDiscountPolicy implements DiscountPolicy {
    static int EVENT_START_DAY = 1;

    @Override
    public int calculateGiveawayItem(){
        return (GIVEAWAY_DISCOUNT.getStandardAmount())*-1;
    }
    
    @Override
    public int calculateDDayDiscountPrice(int visitDay) {
        return (DDAY_DISCOUNT.getStandardAmount()
                + (visitDay - EVENT_START_DAY) * DDAY_DISCOUNT.getIncreaseAmount()) * -1;
    }

    @Override
    public int calculateWeekdayDiscountPrice(int dessertQuantity) {
        return (WEEKDAY_DISCOUNT.getIncreaseAmount() * dessertQuantity) * -1;
    }

    @Override
    public int calculateWeekendDiscountPrice(int mainQuantity) {
        return (WEEKEND_DISCOUNT.getIncreaseAmount() * mainQuantity) * -1;
    }

    @Override
    public int calculateSpecialDiscountPrice() {
        return (SPECIAL_DISCOUNT.getStandardAmount()) * -1;
    }
}
