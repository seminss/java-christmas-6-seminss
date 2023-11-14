package christmas.service;

import christmas.model.vo.DiscountAmount;
import christmas.model.DiscountBenefits;
import christmas.model.Order;
import christmas.model.VisitDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.AbstractMap;
import java.util.List;

import static christmas.model.policy.discount.DiscountConfig.*;
import static org.junit.jupiter.api.Assertions.assertTrue;


class ChristmasDiscountCalculatorTest {

    @DisplayName("17일에는 평일 할인, 특별 할인, 증정 메뉴 제공, 크리스마스 디데이 할인이 적용된다.")
    @Test
    public void calculateDiscountsTest() {
        VisitDate visitDate = new VisitDate(17);
        Order order = new Order(
                List.of(
                        new AbstractMap.SimpleEntry<>("바비큐립", 1),
                        new AbstractMap.SimpleEntry<>("해산물파스타", 5),
                        new AbstractMap.SimpleEntry<>("초코케이크", 2),
                        new AbstractMap.SimpleEntry<>("샴페인", 1)));
        ChristmasDiscountCalculator calculator = new ChristmasDiscountCalculator();

        DiscountBenefits discountBenefits = calculator.calculateDiscounts(visitDate, order);
        List<DiscountAmount> discounts = discountBenefits.getDiscounts();

        System.out.println(discounts);

        assertTrue(discounts.contains(new DiscountAmount(WEEKDAY_DISCOUNT, 2023 * 2)),
                "평일 할인 문제");

        assertTrue(discounts.contains(new DiscountAmount(WEEKEND_DISCOUNT, 0)),
                "주말 할인 문제");

        assertTrue(discounts.contains(new DiscountAmount(SPECIAL_DISCOUNT, 1000)),
                "특별 할인 문제");

        assertTrue(discounts.contains(new DiscountAmount(DDAY_DISCOUNT, (17 - 1) * 100 + 1000)),
                "크리스마스 디데이 할인 문제");

    }
}