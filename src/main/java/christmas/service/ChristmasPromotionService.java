package christmas.service;

import christmas.model.*;
import christmas.model.calendar.ChristmasEventCalendar;
import christmas.model.calendar.EventCalendar;
import christmas.model.discount.ChristmasDiscountPolicy;
import christmas.model.discount.DiscountPolicy;
import christmas.model.valueObject.InitialOrderAmount;
import christmas.model.valueObject.VisitDate;

import java.time.LocalDate;
import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class ChristmasPromotionService {
    private VisitDate visitDate;
    private Order order;
    private InitialOrderAmount initialOrderAmount;
    private final DiscountPolicy discountPolicy = new ChristmasDiscountPolicy();
    private final EventCalendar eventCalendar = new ChristmasEventCalendar(
            new HashSet<>(Arrays.asList(
                    LocalDate.of(2023, 12, 3),
                    LocalDate.of(2023, 12, 10),
                    LocalDate.of(2023, 12, 17),
                    LocalDate.of(2023, 12, 24),
                    LocalDate.of(2023, 12, 25),
                    LocalDate.of(2023, 12, 31) //TODO: 규칙으로 초기화 하기
            ))
    );
    private final Menu giveawayMenu = Menu.CHAMPAGNE;

    public void createVisitDate(Integer readVisitDate) {
        visitDate = new VisitDate(readVisitDate);
    }

    public void createOrder(List<SimpleEntry<String, Integer>> readOrder) {
        order = new Order(readOrder);
    }

    public void calculateInitialOrderAmount() {
        int amount = 0;
        for (Menu menu : Menu.values()) {
            int quantity = order.getOrder().getOrDefault(menu, 0);
            amount += quantity * menu.getPrice();
        }
        initialOrderAmount = new InitialOrderAmount(amount);
    }

    public int getInitialOrderAmount() {
        return initialOrderAmount.amount();
    }

    public boolean checkEventQualification() {
        return initialOrderAmount.amount() > 10000;
    }

    public boolean checkGiveawayEventQualification() { // TODO : 얘도 상태 정보로 저장 해야 할까??
        boolean amountCondition = initialOrderAmount.amount() > giveawayMenu.getPrice();
        return amountCondition;
    }

    public void discount() {
        int discountPrice = 0;
        if (eventCalendar.isDDayDiscountDay(visitDate.getDate())) {
            discountPrice += discountPolicy.getDDayDiscountPrice(visitDate.getDate().getDayOfMonth());
        }
//        if (eventCalendar.isWeekday(visitDate.getDate())) {
//            int dessertQuantity = order.getOrder().getDessertQuantity();
//            discountPrice += discountPolicy.getWeekdayDiscountPrice(dessertQuantity);
//        }
//        if (eventCalendar.isWeekend(visitDate.getDate())) {
//            int mainQuantity = order.getOrder().getMainQuantity();
//            discountPrice += discountPolicy.getWeekendDiscountPrice(mainQuantity);
//        }
//        if (eventCalendar.isSpecialDiscountDay(visitDate.getDate())) {
//            discountPrice += discountPolicy.getSpecialDiscountPrice();
//        }
    }
}