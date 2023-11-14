package christmas.model.policy.discount;

public interface DiscountPolicy {
    int calculateDDayDiscountPrice(int visitDay);

    int calculateWeekdayDiscountPrice(int dessertQuantity);

    int calculateWeekendDiscountPrice(int mainQuantity);

    int calculateSpecialDiscountPrice();

    int calculateGiveawayItem();
}