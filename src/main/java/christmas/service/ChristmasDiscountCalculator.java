package christmas.service;

import christmas.model.constant.Calender;
import christmas.model.policy.calendar.ChristmasEventSchedular;
import christmas.model.policy.calendar.EventSchedular;
import christmas.model.policy.discount.ChristmasDiscountPolicy;
import christmas.model.policy.discount.DiscountPolicy;
import christmas.model.vo.DiscountAmount;
import christmas.model.DiscountedItems;
import christmas.model.Order;
import christmas.model.VisitDate;

import java.util.ArrayList;
import java.util.List;

import static christmas.constant.EventThreshold.GIVEAWAY_THRESHOLD;
import static christmas.model.policy.discount.DiscountSettings.*;

public class ChristmasDiscountCalculator {
    private final EventSchedular eventSchedular;
    private final DiscountPolicy discountPolicy;

    public ChristmasDiscountCalculator() {
        this.eventSchedular = new ChristmasEventSchedular();
        this.discountPolicy = new ChristmasDiscountPolicy();
    }

    public DiscountedItems calculateDiscounts(VisitDate visitDate, Order order) {
        List<DiscountAmount> discounts = new ArrayList<>();
        discounts.add(discountDDay(visitDate));
        discounts.add(discountWeekDay(visitDate, order));
        discounts.add(discountWeekend(visitDate, order));
        discounts.add(discountSpecialDay(visitDate));
        discounts.add(discountGiveaway(order));
        return new DiscountedItems(discounts);
    }

    private boolean isWeekday(VisitDate visitDate) {
        return Calender.isWeekday(visitDate.date());
    }

    private DiscountAmount discountSpecialDay(VisitDate visitDate) {
        if (eventSchedular.isSpecialDiscountDay(visitDate.date())) {
            int amount = discountPolicy.calculateSpecialDiscountPrice();
            return new DiscountAmount(SPECIAL_DISCOUNT, amount);
        }
        return new DiscountAmount(SPECIAL_DISCOUNT, 0);
    }

    private DiscountAmount discountWeekend(VisitDate visitDate, Order order) {
        if (!isWeekday(visitDate)) {
            int mainQuantity = order.getMainQuantity();
            int amount = discountPolicy.calculateWeekendDiscountPrice(mainQuantity);
            return new DiscountAmount(WEEKEND_DISCOUNT, amount);
        }
        return new DiscountAmount(WEEKEND_DISCOUNT, 0);
    }

    private DiscountAmount discountWeekDay(VisitDate visitDate, Order order) {
        if (isWeekday(visitDate)) {
            int dessertQuantity = order.getDessertQuantity();
            int amount = discountPolicy.calculateWeekdayDiscountPrice(dessertQuantity);
            return new DiscountAmount(WEEKDAY_DISCOUNT, amount);
        }
        return new DiscountAmount(WEEKDAY_DISCOUNT, 0);
    }

    private DiscountAmount discountDDay(VisitDate visitDate) {
        if (eventSchedular.isDDayDiscountDay(visitDate.date())) {
            int amount = discountPolicy.calculateDDayDiscountPrice(visitDate.date().getDayOfMonth());
            return new DiscountAmount(DDAY_DISCOUNT, amount);
        }
        return new DiscountAmount(DDAY_DISCOUNT, 0); //TODO: DiscountDDay가 아니면 early return
    }

    private DiscountAmount discountGiveaway(Order order) {
        if (order.getBaseOrderAmount() > GIVEAWAY_THRESHOLD.getValue()) {
            int amount = discountPolicy.calculateGiveawayItem();
            return new DiscountAmount(GIVEAWAY_DISCOUNT, amount);
        }
        return new DiscountAmount(GIVEAWAY_DISCOUNT, 0);
    }
}
