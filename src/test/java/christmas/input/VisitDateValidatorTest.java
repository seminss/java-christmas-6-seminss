package christmas.input;

import christmas.view.input.validator.VisitDateValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Nested
@DisplayName("방문 날짜 입력 검증 테스트")
public class VisitDateValidatorTest {
    private String userInput;
    private final VisitDateValidator validator = new VisitDateValidator();

    @DisplayName("정수면 성공이다.")
    @Test
    void success1() {
        userInput = "2";
        validator.validate(userInput);
    }

    @DisplayName("음의 정수면 성공이다.")
    @Test
    void success2() {
        userInput = "-20";
        validator.validate(userInput);
    }

    @DisplayName("실수면 예외가 발생한다.")
    @Test
    void fail1() {
        userInput = "5.2";
        Assertions.assertThrows(IllegalArgumentException.class, () -> validator.validate(userInput));
    }

    @Test
    @DisplayName("값이 없으면 예외가 발생한다.")
    void fail2() {
        userInput = "";
        Assertions.assertThrows(IllegalArgumentException.class, () -> validator.validate(userInput));
    }

    @DisplayName("문자가 들어오면 예외가 발생한다.")
    @ValueSource(strings = {"&", "5&"})
    @ParameterizedTest
    void fail3(String userInput) {
        assertThatThrownBy(() -> validator.validate(userInput));
    }

    @DisplayName("공백이 있으면 예외가 발생한다.")
    @ValueSource(strings = {"   5", "5   ", "1    2"})
    @ParameterizedTest
    void fail4(String userInput) {
        assertThatThrownBy(() -> validator.validate(userInput));
    }
}
