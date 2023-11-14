package christmas.service;

import christmas.model.policy.calendar.ChristmasEventCalendar;
import christmas.model.policy.calendar.EventCalendar;
import christmas.model.policy.discount.ChristmasDiscountPolicy;
import christmas.model.policy.discount.DiscountPolicy;
import christmas.model.vo.DiscountAmount;
import christmas.model.DiscountedItems;
import christmas.model.Order;
import christmas.model.VisitDate;

import java.util.ArrayList;
import java.util.List;

import static christmas.model.policy.discount.DiscountSettings.*;

public class ChristmasDiscountCalculator {

    private final int GIVEAWAY_THRESHOLD = 120_000;
    private final EventCalendar eventCalendar;
    private final DiscountPolicy discountPolicy;

    public ChristmasDiscountCalculator() {
        this.eventCalendar = new ChristmasEventCalendar();
        this.discountPolicy = new ChristmasDiscountPolicy();
    }

    public DiscountedItems calculateDiscounts(VisitDate visitDate, Order order) {
        List<DiscountAmount> discounts = new ArrayList<>();
        discounts.add(discountGiveaway(order));
        discounts.add(discountDDay(visitDate));
        discounts.add(discountSpecialDay(visitDate));
        if (isWeekday(visitDate)) {
            discounts.add(discountWeekDay(visitDate, order));
            return new DiscountedItems(discounts);
        }
        discounts.add(discountWeekend(visitDate, order));
        return new DiscountedItems(discounts);
    }

    private boolean isWeekday(VisitDate visitDate) {
        return eventCalendar.isWeekday(visitDate.getDate());
    }

    private DiscountAmount discountSpecialDay(VisitDate visitDate) {
        if (eventCalendar.isSpecialDiscountDay(visitDate.getDate())) {
            int amount = discountPolicy.calculateSpecialDiscountPrice();
            return new DiscountAmount(SPECIAL_DISCOUNT, amount);
        }
        return new DiscountAmount(SPECIAL_DISCOUNT, 0);
    }

    private DiscountAmount discountWeekend(VisitDate visitDate, Order order) {
        if (eventCalendar.isWeekend(visitDate.getDate())) {
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
        if (eventCalendar.isDDayDiscountDay(visitDate.getDate())) {
            int amount = discountPolicy.calculateDDayDiscountPrice(visitDate.getDate().getDayOfMonth());
            return new DiscountAmount(DDAY_DISCOUNT, amount);
        }
        return new DiscountAmount(DDAY_DISCOUNT, 0);
    }

    private DiscountAmount discountGiveaway(Order order) {
        if (order.calculateBaseOrderAmount() > GIVEAWAY_THRESHOLD) {
            int amount = discountPolicy.calculateGiveawayItem();
            return new DiscountAmount(GIVEAWAY_DISCOUNT, amount);
        }
        return new DiscountAmount(GIVEAWAY_DISCOUNT, 0);
    }
}
