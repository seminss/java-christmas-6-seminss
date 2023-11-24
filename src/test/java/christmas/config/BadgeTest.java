package christmas.config;

import christmas.model.constant.Badge;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BadgeTest {

    @DisplayName("총혜택 금액이 5천 원 이상은 별이다.")
    @Test
    void of1() {
        Assertions.assertEquals(Badge.STAR, Badge.of(-5000));
    }

    @DisplayName("총혜택 금액이 1만 원 이상은 트리다.")
    @Test
    void of2() {
        Assertions.assertEquals(Badge.TREE, Badge.of(-10000));
    }

    @DisplayName("총혜택 금액이 2만 원 이상은 산타다.")
    @Test
    void of3() {
        Assertions.assertEquals(Badge.SANTA, Badge.of(-20000));
    }

    @DisplayName("총혜택 금액이 5천 원 미만은 뱃지가 없다.")
    @Test
    void of4() {
        Assertions.assertEquals(Badge.NONE, Badge.of(-3000));
    }

}