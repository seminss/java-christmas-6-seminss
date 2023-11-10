package christmas.input;

import christmas.view.input.validator.OrderMenuValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@Nested
@DisplayName("주문 메뉴와 개수 입력 검증 테스트")
public class OrderMenuValidatorTest {
    private final OrderMenuValidator validator = new OrderMenuValidator();

    @DisplayName("메뉴-개수가 쉼표로 구분되면 성공한다.")
    @ValueSource(strings = {"해산물파스타-2,레드와인-1,초코케이크-1", "시저샐러드-20,시저샐러드-1", "시저샐러드-10"})
    @ParameterizedTest
    void success1(String userInput) {
        validator.validate(userInput);
    }

    @DisplayName("메뉴가 한글이 아니면 예외가 발생한다.")
    @ValueSource(strings = {"seaFoodPasta-2,redWine-1,choco-1"})
    @ParameterizedTest
    void fail1(String userInput) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> validator.validate(userInput));
    }

    @DisplayName("주문 개수가 양의 정수가 아니면 예외가 발생한다.")
    @ValueSource(strings = {"레드와인-10,초코케이크-1.0", "해산물파스타--2, 초코케이크-1",})
    @ParameterizedTest
    void fail2(String userInput) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> validator.validate(userInput));
    }

    @DisplayName("구분자가 쉼표가 아니면 예외가 발생한다.")
    @ValueSource(strings = {"해산물파스타-2 레드와인-1", "해산물파스타-2;레드와인-1;초코케이크-1"})
    @ParameterizedTest
    void fail3(String userInput) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> validator.validate(userInput));
    }

    @DisplayName("연결자가 하이픈이 아니면 예외가 발생한다.")
    @ValueSource(strings = {"해산물파스타+2,레드와인+1,초코케이크=1"})
    @ParameterizedTest
    void fail4(String userInput) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> validator.validate(userInput));
    }
}
