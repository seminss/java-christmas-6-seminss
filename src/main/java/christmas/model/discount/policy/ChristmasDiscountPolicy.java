package christmas.model.discount.policy;

public class ChristmasDiscountPolicy implements DiscountPolicy {
    static int DDay = 1;
    static int STANDARD_AMOUNT = 1000;
    static int INCREASE_AMOUNT = 100;
    static int DISCOUNT_PRICE_PER_MENU=2023;
    static int SPECIAL_DISCOUNT_PRICE=1000; //TODO: DiscountType에 합쳐도 될 듯

    @Override
    public int getDDayDiscountPrice(int visitDay) {
        return STANDARD_AMOUNT + (visitDay - DDay) * INCREASE_AMOUNT;
    }

    @Override
    public int getWeekdayDiscountPrice(int dessertQuantity) {
        return DISCOUNT_PRICE_PER_MENU*dessertQuantity;
    }

    @Override
    public int getWeekendDiscountPrice(int mainQuantity) {
        return DISCOUNT_PRICE_PER_MENU * mainQuantity;
    }

    @Override
    public int getSpecialDiscountPrice() {
        return SPECIAL_DISCOUNT_PRICE;
    }

}
