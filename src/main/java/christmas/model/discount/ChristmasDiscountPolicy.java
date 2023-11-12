package christmas.model.discount;
public class ChristmasDiscountPolicy implements DiscountPolicy {
    static int DDay = 1;
    static int STANDARD_AMOUNT = 1000;
    static int INCREASE_AMOUNT = 100;

    public int getDDayDiscountPrice(int day) {
        return STANDARD_AMOUNT + (day-DDay) * INCREASE_AMOUNT;
    }

}
