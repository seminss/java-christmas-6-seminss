package christmas.model.order;

import christmas.exception.business.InvalidOrderException;
import christmas.model.Order;
import christmas.view.input.OrderRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Order 모델 validate 테스트")
class OrderValidationTest {

    private static Stream<OrderRequest> provideStringsForValidateDrinksOnly() {
        return Stream.of(
                OrderRequest.of("제로콜라-1,레드와인-1"),
                OrderRequest.of("샴페인-1")
        );
    }

    private static Stream<OrderRequest> provideStringsForValidateMenuExistence() {
        return Stream.of(
                OrderRequest.of("하늘보리-1,바비큐립-1"),
                OrderRequest.of("하늘보리-1")
        );
    }

    private static Stream<OrderRequest> provideStringsForValidateSingleQuantity() {
        return Stream.of(
                OrderRequest.of("제로콜라-0,바비큐립-1"),
                OrderRequest.of("바비큐립-0")
        );
    }

    private static Stream<OrderRequest> provideStringsForValidateTotalQuantity() {
        return Stream.of(
                OrderRequest.of("제로콜라-4,바비큐립-19"),
                OrderRequest.of("샴페인-21")
        );
    }

    private static Stream<OrderRequest> provideStringsForValidateMenuDuplication() {
        return Stream.of(
                OrderRequest.of("시저샐러드-5,시저샐러드-5")
        );
    }

    private static Stream<OrderRequest> provideStringsForSuccess() {
        return Stream.of(
                OrderRequest.of("시저샐러드-5,샴페인-1"),
                OrderRequest.of("시저샐러드-15,샴페인-1"),
                OrderRequest.of("시저샐러드-20"),
                OrderRequest.of("시저샐러드-1")
        );
    }

    @DisplayName("성공 테스트")
    @ParameterizedTest
    @MethodSource("provideStringsForSuccess")
    void success(OrderRequest orderList) {
        assertDoesNotThrow(() -> Order.of(orderList));
    }

    @DisplayName("입력에 음료만 있으면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("provideStringsForValidateDrinksOnly")
    void fail1(OrderRequest orderList) {
        assertThrows(InvalidOrderException.class, () -> Order.of(orderList));
    }

    @DisplayName("메뉴에 없는 메뉴를 입력하면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("provideStringsForValidateMenuExistence")
    void fail2(OrderRequest orderList) {
        assertThrows(InvalidOrderException.class, () -> Order.of(orderList));
    }


    @DisplayName("수량이 1 이상이 아니면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("provideStringsForValidateSingleQuantity")
    void fail3(OrderRequest orderList) {
        assertThrows(InvalidOrderException.class, () -> Order.of(orderList));
    }


    @DisplayName("총 수량이 20을 넘어가면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("provideStringsForValidateTotalQuantity")
    void fail4(OrderRequest orderList) {
        assertThrows(InvalidOrderException.class, () -> Order.of(orderList));
    }

    @DisplayName("메뉴를 중복해서 입력하면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("provideStringsForValidateMenuDuplication")
    void fail5(OrderRequest orderList) {
        assertThrows(InvalidOrderException.class, () -> Order.of(orderList));
    }

}
