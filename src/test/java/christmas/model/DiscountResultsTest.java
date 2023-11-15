package christmas.model;

import christmas.model.policy.discount.DiscountSettings;
import christmas.model.vo.DiscountAmount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class DiscountResultsTest {

    @DisplayName("증정품을 받았을 때 총 혜택 금액에는 포함이 되지만, 예상 결제 금액에서 할인은 되지 않는다.")
    @Test
    void finalPaymentAmountTest1() {
        List<DiscountAmount> discountAmounts = List.of(
                new DiscountAmount(DiscountSettings.GIVEAWAY_DISCOUNT, -25000)
        );
        DiscountedItems discountedItems = new DiscountedItems(discountAmounts);
        DiscountResults discountResults = new DiscountResults(140000, discountedItems);

        Assertions.assertEquals(discountResults.getTotalDiscountAmount(), -25000);
        Assertions.assertEquals(discountResults.getFinalPaymentAmount(), 140000);
    }

    @DisplayName("증정품을 받지 않으면 할인 후 예상 결제 금액은 총 주문 금액 - 총 혜택 금액이다.")
    @Test
    void finalPaymentAmountTest2() {
        List<DiscountAmount> discountAmounts = List.of(
                new DiscountAmount(DiscountSettings.WEEKDAY_DISCOUNT, -1000)
        );
        DiscountedItems discountedItems = new DiscountedItems(discountAmounts);
        DiscountResults discountResults = new DiscountResults(10000, discountedItems);

        Assertions.assertEquals(discountResults.getTotalDiscountAmount(), -1000);
        Assertions.assertEquals(discountResults.getFinalPaymentAmount(), 10000 - 1000);
    }
}