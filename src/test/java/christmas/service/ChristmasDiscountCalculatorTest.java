package christmas.service;

import christmas.model.vo.DiscountAmount;
import christmas.model.DiscountedItems;
import christmas.model.Order;
import christmas.model.VisitDate;
import christmas.view.input.DateRequest;
import christmas.view.input.OrderRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static christmas.model.policy.discount.DiscountSettings.*;
import static org.junit.jupiter.api.Assertions.assertTrue;


class ChristmasDiscountCalculatorTest {

    private VisitDate visitDate;
    private ChristmasDiscountCalculator calculator;

    @BeforeEach
    public void SetUp() {
        visitDate = VisitDate.of(DateRequest.of("17"));
        calculator = new ChristmasDiscountCalculator();
    }

    @DisplayName("평일 이벤트와 주말 이벤트는 동시에 존재할 수 없다.")
    @Test
     void calculateDiscountsTest1() {
        //given
        Order order = Order.of(OrderRequest.of("바비큐립-1,해산물파스타-5,초코케이크-2,샴페인-1"));

        //when
        DiscountedItems discountedItems = calculator.calculateDiscounts(visitDate, order);
        List<DiscountAmount> discounts = discountedItems.items();

        //then //TODO: assertSoftly
        assertTrue(discounts.contains(new DiscountAmount(WEEKDAY_DISCOUNT, -4046)),
                "평일 할인은 2023*2 만큼 들어간다.");

        assertTrue(discounts.contains(new DiscountAmount(WEEKEND_DISCOUNT, 0)),
                "주말 할인은 포함 되면 안된다.");

        assertTrue(discounts.contains(new DiscountAmount(SPECIAL_DISCOUNT, -1000)),
                "특별 할인일에 포함되어, 1000원이 할인이 들어간다.");

        assertTrue(discounts.contains(new DiscountAmount(DDAY_DISCOUNT, -2600)),
                "크리스마스 디데이 할인은 16*10 + 1000 만큼 들어간다.");

        assertTrue(discounts.contains(new DiscountAmount(GIVEAWAY_DISCOUNT, -25000)),
                "증정 이벤트 할인은 샴페인 가격(25000)만큼 할인된다.");

    }

    @DisplayName("12만원을 넘지 않으면 증정 이벤트 금액은 0이다.")
    @Test
     void calculateDiscountsTest2() {
        //given
        Order order = Order.of(OrderRequest.of("바비큐립-1"));

        //when
        DiscountedItems discountedItems = calculator.calculateDiscounts(visitDate, order);
        List<DiscountAmount> discounts = discountedItems.items();

        //then
        assertTrue(discounts.contains(new DiscountAmount(GIVEAWAY_DISCOUNT, 0)),
                "증정 이벤트 할인이 적용 되면 안된다.");
    }
}