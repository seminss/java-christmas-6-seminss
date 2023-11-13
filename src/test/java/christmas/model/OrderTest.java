package christmas.model;

import christmas.model.menu.Menu;
import christmas.model.order.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.AbstractMap;
import java.util.List;

@DisplayName("Order 모델 연산 테스트")
public class OrderTest {
    Order order;

    @BeforeEach
    public void setUp() {
        order = new Order(List.of(
                new AbstractMap.SimpleEntry<>("바비큐립", 1),
                new AbstractMap.SimpleEntry<>("해산물파스타", 5),
                new AbstractMap.SimpleEntry<>("초코케이크", 2),
                new AbstractMap.SimpleEntry<>("샴페인", 1)));
    }

    @DisplayName("할인 전, 총 주문 금액을 연산한다.")
    @Test
    public void calculateInitialOrderAmountTest() {
        int expectedAmount = Menu.SEAFOOD_PASTA.getPrice() * 5 +
                Menu.BBQ_RIBS.getPrice()+
                Menu.CHOCOLATE_CAKE.getPrice() * 2 +
                Menu.CHAMPAGNE.getPrice();
        Assertions.assertEquals(expectedAmount, order.getInitialOrderAmount());
    }

    @DisplayName("메인 메뉴 개수를 반환한다.")
    @Test
    public void getMainQuantityTest() {
        int expectedAmount = 6;
        Assertions.assertEquals(expectedAmount, order.getMainQuantity());
    }

    @DisplayName("디저트 개수를 반환한다.")
    @Test
    public void getDessertQuantityTest() {
        int expectedAmount = 2;
        Assertions.assertEquals(expectedAmount, order.getDessertQuantity());
    }
}
