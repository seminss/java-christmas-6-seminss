package christmas.model;

import christmas.model.constant.Menu;
import christmas.dto.request.OrderRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Order 모델 연산 테스트")
class OrderTest {
    Order order;

    @BeforeEach
    public void setUp() {
        order = Order.of(OrderRequest.of("바비큐립-1,해산물파스타-5,초코케이크-2,샴페인-1"));
    }

    @DisplayName("할인 전, 총 주문 금액을 연산한다.")
    @Test
    void calculateInitialOrderAmountTest() {
        int expectedAmount = Menu.SEAFOOD_PASTA.getPrice() * 5 +
                Menu.BBQ_RIBS.getPrice() +
                Menu.CHOCOLATE_CAKE.getPrice() * 2 +
                Menu.CHAMPAGNE.getPrice();
        Assertions.assertEquals(expectedAmount, order.getBaseOrderAmount());
    }

    @DisplayName("메인 메뉴 개수를 반환한다.")
    @Test
    void getMainQuantityTest() {
        int expectedAmount = 6;
        Assertions.assertEquals(expectedAmount, order.getMainQuantity());
    }

    @DisplayName("디저트 개수를 반환한다.")
    @Test
    void getDessertQuantityTest() {
        int expectedAmount = 2;
        Assertions.assertEquals(expectedAmount, order.getDessertQuantity());
    }
}
