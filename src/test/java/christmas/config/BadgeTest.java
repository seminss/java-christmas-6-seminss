package christmas.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BadgeTest {

    @DisplayName("총혜택 금액이 5천 원 이상은 별이다.")
    @Test
    void of1() {
        Assertions.assertEquals(Badge.of(-5000), Badge.STAR);
    }

    @DisplayName("총혜택 금액이 1만 원 이상은 트리다.")
    @Test
    void of2() {
        Assertions.assertEquals(Badge.of(-10000), Badge.TREE);
    }

    @DisplayName("총혜택 금액이 2만 원 이상은 산타다.")
    @Test
    void of3() {
        Assertions.assertEquals(Badge.of(-20000), Badge.SANTA);
    }

    @DisplayName("총혜택 금액이 5천 원 미만은 뱃지가 없다.")
    @Test
    void of4() {
        Assertions.assertNull(Badge.of(-3000));
    }

}