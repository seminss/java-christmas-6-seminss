package christmas.model.policy.discount;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@Nested
@DisplayName("ChristmasDiscountPolicy 의 할인 금액 반환 테스트")
public class ChristmasDiscountPolicyTest {

    private final DiscountPolicy discountPolicy = new ChristmasDiscountPolicy();

    @DisplayName("증정 이벤트 할인 가격은 25,000(샴페인1개)다.")
    @Test
    void calculateGiveawayItem() {
        Assertions.assertEquals(discountPolicy.calculateGiveawayItem(), -25000);
    }

    @DisplayName("1,000원으로 시작하여 크리스마스가 다가올수록 날마다 할인 금액이 100원씩 증가한다.")
    @CsvSource({
            "1, -1000",  // 12/1은 1000원
            "2, -1100", // 12/2는 1100원
            "25, -3400", // 12/25는 3400원
    })
    @ParameterizedTest
    void calculateDDayDiscountPrice(int day, int expectedDiscount) {
        Assertions.assertEquals(
                discountPolicy.calculateDDayDiscountPrice(day), expectedDiscount
        );
    }

    @DisplayName("평일 할인은 디저트 메뉴 1개당 2023원 할인이 된다.")
    @Test
    void calculateWeekdayDiscountPrice() {
        Assertions.assertEquals(
                discountPolicy.calculateWeekdayDiscountPrice(5), -2023 * 5
        );
    }

    @DisplayName("주말 할인은 메인 메뉴 1개당 2023원 할인이 된다.")
    @Test
    void calculateWeekendDiscountPrice() {
        Assertions.assertEquals(
                discountPolicy.calculateWeekdayDiscountPrice(5), -2023 * 5
        );
    }

    @DisplayName("특별 할인은 특별 할인 일에 1000원 할인 한다.")
    @Test
    void calculateSpecialDiscountPrice() {
        Assertions.assertEquals(
                discountPolicy.calculateSpecialDiscountPrice(), -1000
        );
    }
}