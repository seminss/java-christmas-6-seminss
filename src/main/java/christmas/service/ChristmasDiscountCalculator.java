package christmas.service;

import christmas.model.calendar.ChristmasEventCalendar;
import christmas.model.calendar.EventCalendar;
import christmas.model.discount.policy.ChristmasDiscountPolicy;
import christmas.model.discount.policy.DiscountPolicy;
import christmas.model.discount.DiscountAmount;
import christmas.model.promotion.Giveaway;
import christmas.model.discount.DiscountResult;
import christmas.model.order.Order;
import christmas.model.valueObject.VisitDate;

import java.util.ArrayList;
import java.util.List;

import static christmas.model.discount.DiscountType.*;

public class ChristmasDiscountCalculator {
    private final EventCalendar eventCalendar;
    private final DiscountPolicy discountPolicy;
    private final VisitDate visitDate;
    private final Order order;

    public ChristmasDiscountCalculator(VisitDate visitDate, Order order) {
        this.visitDate = visitDate;
        this.order = order;
        this.eventCalendar = new ChristmasEventCalendar();
        this.discountPolicy = new ChristmasDiscountPolicy();
    }

    public DiscountResult calculateDiscounts() {
        List<DiscountAmount> discounts = new ArrayList<>();
        discounts.add(discountWeekend());
        discounts.add(discountWeekDay());
        discounts.add(discountSpecialDay());
        discounts.add(discountDDay());
        discounts.add(discountByGiveaway());
        return new DiscountResult(discounts);
    }

    private DiscountAmount discountSpecialDay() {
        if (eventCalendar.isSpecialDiscountDay(visitDate.getDate())) {
            int amount = discountPolicy.getSpecialDiscountPrice();
            return new DiscountAmount(SPECIAL_DISCOUNT, amount);
        }
        return new DiscountAmount(SPECIAL_DISCOUNT, 0);
    }

    private DiscountAmount discountWeekend() {
        if (eventCalendar.isWeekend(visitDate.getDate())) {
            int mainQuantity = order.getMainQuantity();
            int amount = discountPolicy.getWeekendDiscountPrice(mainQuantity);
            return new DiscountAmount(WEEKEND_DISCOUNT, amount);
        }
        return new DiscountAmount(WEEKEND_DISCOUNT, 0);
    }

    private DiscountAmount discountWeekDay() {
        if (eventCalendar.isWeekday(visitDate.getDate())) {
            int dessertQuantity = order.getDessertQuantity();
            int amount = discountPolicy.getWeekdayDiscountPrice(dessertQuantity);
            return new DiscountAmount(WEEKDAY_DISCOUNT, amount);
        }
        return new DiscountAmount(WEEKDAY_DISCOUNT, 0);
    }

    private DiscountAmount discountDDay() {
        if (eventCalendar.isDDayDiscountDay(visitDate.getDate())) {
            int amount = discountPolicy.getDDayDiscountPrice(visitDate.getDate().getDayOfMonth());
            return new DiscountAmount(DDAY_DISCOUNT, amount);
        }
        return new DiscountAmount(DDAY_DISCOUNT, 0);
    }

    private DiscountAmount discountByGiveaway() {
        if (order.getInitialOrderAmount() > 120000) {
            int amount = Giveaway.getGiveaway().getPrice();
            return new DiscountAmount(GIVEAWAY_DISCOUNT, amount);
        }
        return new DiscountAmount(GIVEAWAY_DISCOUNT, 0);
    }
}
