package christmas.model;

import christmas.model.discount.policy.ChristmasDiscountPolicy;
import christmas.model.discount.policy.DiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@Nested
@DisplayName("ChristmasDiscountPolicy 의 할인 금액 반환 테스트")
public class DiscountPolicyTest {

    private final DiscountPolicy discountPolicy = new ChristmasDiscountPolicy();

    @DisplayName("1,000원으로 시작하여 크리스마스가 다가올수록 날마다 할인 금액이 100원씩 증가한다.")
    @CsvSource({
            "1, 1000",  // 12/1은 1000원
            "2, 1100", // 12/2는 1100원
            "25, 3400", // 12/25는 3400원
    })
    @ParameterizedTest
    public void DDayDiscountPolicyTest(int day, int expectedDiscount) {
        Assertions.assertEquals(discountPolicy.calculateDDayDiscountPrice(day), expectedDiscount);
    }
}
