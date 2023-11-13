package christmas.service;

import christmas.model.calendar.ChristmasEventCalendar;
import christmas.model.calendar.EventCalendar;
import christmas.model.discount.policy.ChristmasDiscountPolicy;
import christmas.model.discount.policy.DiscountPolicy;
import christmas.model.discount.DiscountAmount;
import christmas.model.discount.DiscountResult;
import christmas.model.menu.Menu;
import christmas.model.order.Order;
import christmas.model.vo.VisitDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static christmas.model.discount.policy.DiscountConfig.*;

public class ChristmasDiscountCalculator {

    private final int GIVEAWAY_THRESHOLD = 120_000;
    private final EventCalendar eventCalendar;
    private final DiscountPolicy discountPolicy;

    public ChristmasDiscountCalculator() {
        this.eventCalendar = new ChristmasEventCalendar();
        this.discountPolicy = new ChristmasDiscountPolicy();
    }

    public DiscountResult calculateDiscounts(VisitDate visitDate, Order order) {
        List<DiscountAmount> discounts = new ArrayList<>();
        discounts.add(discountWeekend(visitDate, order));
        discounts.add(discountWeekDay(visitDate, order));
        discounts.add(discountSpecialDay(visitDate));
        discounts.add(discountDDay(visitDate));
        Optional<Menu> giveawayItem = determineGiveaway(order);
        return new DiscountResult(discounts, giveawayItem);
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
        if (eventCalendar.isWeekday(visitDate.getDate())) {
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

    private Optional<Menu> determineGiveaway(Order order) {
        if (order.getInitialOrderAmount() > GIVEAWAY_THRESHOLD) {
            return Optional.of(discountPolicy.getGiveawayItem());
        }
        return Optional.empty();
    }
}
