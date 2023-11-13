package christmas.model.discount.policy;

import christmas.model.menu.Menu;

public interface DiscountPolicy {
    int calculateDDayDiscountPrice(int visitDay);

    int calculateWeekdayDiscountPrice(int dessertQuantity);

    int calculateWeekendDiscountPrice(int mainQuantity);

    int calculateSpecialDiscountPrice();

    Menu getGiveawayItem();
}
