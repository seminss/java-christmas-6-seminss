package christmas.model.order;

import christmas.exception.business.InvalidOrderException;
import christmas.model.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Order 모델 validate 테스트")
public class OrderValidationTest {

    private static Stream<List<SimpleEntry<String, Integer>>> provideStringsForValidateDrinksOnly() {
        return Stream.of(
                List.of(new SimpleEntry<>("제로콜라", 1), new SimpleEntry<>("레드와인", 1)),
                List.of(new SimpleEntry<>("샴페인", 1))
        );
    }

    private static Stream<List<SimpleEntry<String, Integer>>> provideStringsForValidateMenuExistence() {
        return Stream.of(
                List.of(new SimpleEntry<>("하늘보리", 1), new SimpleEntry<>("바비큐립", 1)),
                List.of(new SimpleEntry<>("하늘보리", 1))
        );
    }

    private static Stream<List<SimpleEntry<String, Integer>>> provideStringsForValidateSingleQuantity() {
        return Stream.of(
                List.of(new SimpleEntry<>("제로콜라", 0), new SimpleEntry<>("바비큐립", 1)),
                List.of(new SimpleEntry<>("바비큐립", 0))
        );
    }

    private static Stream<List<SimpleEntry<String, Integer>>> provideStringsForValidateTotalQuantity() {
        return Stream.of(
                List.of(new SimpleEntry<>("제로콜라", 5), new SimpleEntry<>("바비큐립", 18)),
                List.of(new SimpleEntry<>("샴페인", 21))
        );
    }

    private static Stream<List<SimpleEntry<String, Integer>>> provideStringsForValidateMenuDuplication() {
        return Stream.of(
                List.of(new SimpleEntry<>("시저샐러드", 5), new SimpleEntry<>("시저샐러드", 1))
        );
    }

    private static Stream<List<SimpleEntry<String, Integer>>> provideStringsForSuccess() {
        return Stream.of(
                List.of(new SimpleEntry<>("시저샐러드", 5), new SimpleEntry<>("샴페인", 1)),
                List.of(new SimpleEntry<>("시저샐러드", 15), new SimpleEntry<>("샴페인", 1)),
                List.of(new SimpleEntry<>("시저샐러드", 20)),
                List.of(new SimpleEntry<>("시저샐러드", 1))
        );
    }

    @DisplayName("성공 테스트")
    @ParameterizedTest
    @MethodSource("provideStringsForSuccess")
    public void success(List<SimpleEntry<String, Integer>> orderList) {
        assertDoesNotThrow(() -> new Order(orderList));

    }

    @DisplayName("입력에 음료만 있으면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("provideStringsForValidateDrinksOnly")
    public void fail1(List<SimpleEntry<String, Integer>> orderList) {
        assertThrows(InvalidOrderException.class, () -> new Order(orderList));
    }

    @DisplayName("메뉴에 없는 메뉴를 입력하면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("provideStringsForValidateMenuExistence")
    public void fail2(List<SimpleEntry<String, Integer>> orderList) {
        assertThrows(InvalidOrderException.class, () -> new Order(orderList));
    }


    @DisplayName("수량이 1 이상이 아니면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("provideStringsForValidateSingleQuantity")
    public void fail3(List<SimpleEntry<String, Integer>> orderList) {
        assertThrows(InvalidOrderException.class, () -> new Order(orderList));
    }


    @DisplayName("총 수량이 20을 넘어가면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("provideStringsForValidateTotalQuantity")
    public void fail4(List<SimpleEntry<String, Integer>> orderList) {
        assertThrows(InvalidOrderException.class, () -> new Order(orderList));
    }

    @DisplayName("메뉴를 중복해서 입력하면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("provideStringsForValidateMenuDuplication")
    public void fail5(List<SimpleEntry<String, Integer>> orderList) {
        assertThrows(InvalidOrderException.class, () -> new Order(orderList));
    }

}
