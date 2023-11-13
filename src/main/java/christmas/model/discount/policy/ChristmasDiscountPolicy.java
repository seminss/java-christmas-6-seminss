package christmas.model.discount.policy;

import christmas.model.menu.Menu;

import static christmas.model.discount.policy.DiscountConfig.*;

public class ChristmasDiscountPolicy implements DiscountPolicy {
    static int EVENT_START_DAY = 1;
    static Menu GIVEAWAY_ITEM = Menu.CHAMPAGNE;

    public Menu getGiveawayItem() {
        return GIVEAWAY_ITEM;
    }

    @Override
    public int calculateDDayDiscountPrice(int visitDay) {
        return DDAY_DISCOUNT.getStandardAmount()
                + (visitDay - EVENT_START_DAY) * DDAY_DISCOUNT.getIncreaseAmount();
    }

    @Override
    public int calculateWeekdayDiscountPrice(int dessertQuantity) {
        return WEEKDAY_DISCOUNT.getIncreaseAmount() *dessertQuantity;
    }

    @Override
    public int calculateWeekendDiscountPrice(int mainQuantity) {
        return WEEKEND_DISCOUNT.getIncreaseAmount() * mainQuantity;
    }

    @Override
    public int calculateSpecialDiscountPrice() {
        return SPECIAL_DISCOUNT.getStandardAmount();
    }
}
