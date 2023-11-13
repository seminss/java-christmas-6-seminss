package christmas.model.discount.policy;

public interface DiscountPolicy {
    int getDDayDiscountPrice(int visitDay);

    int getWeekdayDiscountPrice(int dessertQuantity);

    int getWeekendDiscountPrice(int mainQuantity);

    int getSpecialDiscountPrice();
}
